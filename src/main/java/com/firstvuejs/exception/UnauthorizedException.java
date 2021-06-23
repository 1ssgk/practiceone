package com.firstvuejs.exception;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class UnauthorizedException extends RuntimeException {

    private static final Logger log = LoggerFactory.getLogger(UnauthorizedException.class);

    private static final long serialVersionUID = 1L;

    
	public UnauthorizedException(String message) {
		System.err.println("왜 에러가 나는 거야 : "+message);
    }
}
