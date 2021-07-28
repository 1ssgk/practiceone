package com.firstvuejs.practiceone.controller.users;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.firstvuejs.exception.UnauthorizedException;
import com.firstvuejs.practiceone.service.jwt.JwtService;
import com.firstvuejs.practiceone.service.users.UserService;
import com.google.common.collect.Maps;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import io.jsonwebtoken.lang.Collections;
// 테스트1

// 테스트2

// 테스트3

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    
    //private Logger LOGGER = LoggerFactory.getLogger(RestfulController.class);
    private static final Logger logger = LogManager.getLogger(UserController.class);

    // @PostMapping("/test")
    // public String test(@RequestBody String userData, HttpServletRequest request,HttpServletResponse response){
    //     System.out.println("테스튼데 못뚫지?");
        
    //     String token = request.getHeader("authtoken");
    //     System.out.println("api/users/api token? : "+token);

    //     return "test";
    // }

    @PostMapping("/userJoin/addUser")
    public String userAdd (ModelAndView mav, @RequestBody String userData){
        System.err.println("유저 ADD");
        System.err.println("유저 ADD"+userData);

        try {
            int insertUserRslt = this.userService.insertUser(userData);
            String rsltText = "";
            // 1이면 중복 // 0 이면 성공
            if( insertUserRslt == 0 ){
                rsltText = "0"; // 성공
            }else if( insertUserRslt == 1 ){
                rsltText = "1"; // 중복
            }
            return rsltText;
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "2"; // 실패
        }
    }

    @PostMapping("/userJoin/duplChkUser")
    public String duplChkUser (ModelAndView mav, @RequestBody String userData){
        System.err.println("id 중복체크");
        System.err.println("id 중복체크"+userData);

        try {

            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = mapper.readValue(userData, new TypeReference<Map<String, String>>() {});

            String id = map.get("id");

            int insertUserRslt = this.userService.duplChkUser(id);
            String rsltText = "";
            // 1이면 중복 // 0 이면 성공
            if( insertUserRslt == 0 ){
                rsltText = "0"; // 성공
            }else if( insertUserRslt == 1 ){
                rsltText = "1"; // 중복
            }
            return rsltText;
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            
            return e.getMessage(); // 실패
        }
    }

    @PostMapping("/upload/profile-img")
    public @ResponseBody String requestUploadFile(@RequestParam("fileList") List<MultipartFile> fileList) {
        System.err.println("파일 업로드 서버");
        
        // 폴더를 만들 디렉토리 경로(Window 기반)
        String folderPath = "D:\\vuejsSpring\\practiceone\\frontend\\src\\assets\\img";
        //String folderPath = "D:\\vuejsSpring\\practiceone\\frontend\\src\\assets\\img\\fff\\ddd\\bbb";
        
        File makeFolder = new File(folderPath);

        if(!makeFolder.exists()) {
            // 폴더를 생성합니다.
            makeFolder.mkdirs(); 
            System.out.println("폴더를 생성합니다.");
            
            // 정성적으로 폴더 생성시 true를 반환합니다.
            System.out.println("폴더가 존재하는지 체크 true/false : "+makeFolder.exists());
            
        } else {
            System.out.println("이미 해당 폴더가 존재합니다.");
        }

        try {
            for (MultipartFile multipartFile : fileList) {
                FileOutputStream writer = new FileOutputStream(folderPath+"\\" + multipartFile.getOriginalFilename());
                writer.write(multipartFile.getBytes());
                writer.close();
            }
        } catch (Exception e) {
            System.err.println("에러 : "+e);
            return "upload fail";
        }
        return "upload success";
    }


}