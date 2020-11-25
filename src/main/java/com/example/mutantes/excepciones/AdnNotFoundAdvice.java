package com.example.mutantes.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AdnNotFoundAdvice {
        @ResponseBody
        @ExceptionHandler(AdnNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String adnNotFoundHandler(AdnNotFoundException ex) {
    	return ex.getMessage();
        }
}
