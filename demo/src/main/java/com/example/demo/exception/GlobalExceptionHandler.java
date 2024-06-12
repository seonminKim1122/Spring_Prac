package com.example.demo.exception;

import com.example.demo.common.BasicMessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<BasicMessageDto> handleCustomException(CustomException e) {
        ExceptionStatus exceptionStatus = e.getExceptionStatus();
        return new ResponseEntity<>(new BasicMessageDto(exceptionStatus.getMessage()), exceptionStatus.getStatus());
    }
}
