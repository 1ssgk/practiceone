package com.firstvuejs.practiceone.service.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.firstvuejs.practiceone.service.jwt.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor{

    private static final String HEADER_AUTH = "authtoken";

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // TODO Auto-generated method stub
        final String token = request.getHeader(HEADER_AUTH);

        System.err.println("인터셉터 토큰 걸리냐?"+token);
        
        System.err.println("뭐야이건 "+handler);
        
        if(token.equals("null")){ 
            response.setHeader("authorization", "notLogin");
            return false;
        }else if(token != null && jwtService.isUsable(token)){
            response.setHeader("authorization", "success");
            return true;
        }else{
            response.setHeader("authorization", "tokenIsNull");
            return false;
            //throw new UnauthorizedException("인증 토큰이 없습니다.");
        }
            
    }

}
