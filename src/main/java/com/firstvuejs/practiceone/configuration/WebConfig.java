package com.firstvuejs.practiceone.configuration;

import com.firstvuejs.practiceone.service.interceptor.JwtInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Autowired
    JwtInterceptor jwtInterceptor;

    private static final String[] EXCLUDE_PATHS = {
        "/api/login",
        "/api/file/**",
        "/",
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO Auto-generated method stub
        //WebMvcConfigurer.super.addInterceptors(registry);
        System.err.println("WebConfig.java addInterceptors 입니다");

        registry.addInterceptor(jwtInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns(EXCLUDE_PATHS);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // TODO Auto-generated method stub
		//WebMvcConfigurer.super.addCorsMappings(registry);
		System.err.println("addCorsMappings");
		
		registry.addMapping("/**")
		.allowedOrigins("*")
		.allowedMethods("*")
		.allowedMethods("*")
		.exposedHeaders("authtoken");
    }
}
