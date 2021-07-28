package com.firstvuejs.practiceone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PracticeoneApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(PracticeoneApplication.class, args);
	}

	// @Autowired
	// private JwtInterceptor jwtInterceptor ;

    // private static final String[] EXCLUDE_PATHS = {
    //     "/api/login",
    //     "/"
    // };


	// @Override
	// public void addInterceptors(InterceptorRegistry registry) {
	// 	// TODO Auto-generated method stub
	// 	// WebMvcConfigurer.super.addInterceptors(registry);
	// 	System.err.println("addInterceptors");
	// 	registry.addInterceptor(jwtInterceptor)
    //     .addPathPatterns("/**")
    //     .excludePathPatterns(EXCLUDE_PATHS);
	// }
	
	// @Override
	// public void addCorsMappings(CorsRegistry registry) {
	// 	// TODO Auto-generated method stub
	// 	//WebMvcConfigurer.super.addCorsMappings(registry);
	// 	System.err.println("addCorsMappings");
		
	// 	registry.addMapping("/**")
	// 	.allowedOrigins("*")
	// 	.allowedMethods("*")
	// 	.allowedMethods("*")
	// 	.exposedHeaders("authtoken");
	// }
}
