package com.firstvuejs.exception;

import java.util.Map;

import com.google.common.collect.Maps;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class GlobalRestExceptionHandler {

    @ExceptionHandler(value= { RuntimeException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> internalServerError(Exception e){
        System.err.println("글로벌 에러");
        Map<String,Object> resultMap = Maps.newHashMap();
        resultMap.put("msg", e.getMessage());

        return resultMap;
    }
    
}
