package com.firstvuejs.practiceone.controller.login;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firstvuejs.exception.UnauthorizedException;
import com.firstvuejs.practiceone.service.jwt.JwtService;
import com.firstvuejs.practiceone.service.login.LoginService;
import com.firstvuejs.practiceone.service.users.UserService;
import com.google.common.collect.Maps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.lang.Collections;


@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtService jwtService;
    
    private static final Logger logger = LogManager.getLogger(LoginController.class);

    // 로그인 화면에서 로그인 할 경우
    @PostMapping("/login")
    public Map<String,Object> userLogin (@RequestBody String userData, HttpServletResponse response){
        Map<String,Object> resultMap = Maps.newHashMap();

        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> paramMap = mapper.readValue(userData, new TypeReference<Map<String, Object>>() {});
            Map<String,Object> innerUserData = this.loginService.selectLoginData(paramMap);

            if( Collections.isEmpty(innerUserData) ){ // 없는 ID 입니다.
                response.setHeader("authorization", "noId"); // 
            }
            else if( !paramMap.get("password").equals(innerUserData.get("password"))  ) { // 비밀번호가 틀렸습니다.
                response.setHeader("authorization", "wrongPwd");
            }
            else{
                String token = jwtService.createToken(innerUserData.get("id")+"", (60 * 1000 * 60));
                response.setHeader("authorization", "correctUser");
                response.setHeader("authtoken", token);

                resultMap.putAll(innerUserData);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultMap;
    }

    // 토큰의 유효성 검사
    @PostMapping("/login/authChk")
    public String authChk(@RequestBody String userData, HttpServletRequest request,HttpServletResponse response){
        String token = request.getHeader("authtoken");
        System.out.println("로그인 체크 authChk: "+token);

        return "test";
    }
    // 토큰은 유효하지만 Store의 state가 날라가서 개인정보 다시 가져오기
    @GetMapping("/login/token")
    public Map<String,Object> tokenLogin(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> resultMap = Maps.newHashMap();
        String token = request.getHeader("authtoken");
        
        try {
            String userId = jwtService.getSubject(token);            
            
            Map<String, Object> paramMap = Maps.newHashMap();
            paramMap.put("id", userId);

            resultMap = this.loginService.selectLoginData(paramMap);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultMap;
    }
}