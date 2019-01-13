package com.leo.cloud.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map<String,Object> exceptionHandler(Exception ex){

        System.out.println("server internal error"+ ex.getMessage());
        Map<String,Object> map  = new HashMap<String,Object>();
        map.put("success", false);
        map.put("code",-1);
        map.put("mag",ex.getMessage());

        logger.error("server internal erorr",ex);

        return map;
    }
}