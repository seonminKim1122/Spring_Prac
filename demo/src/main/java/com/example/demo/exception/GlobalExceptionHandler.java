package com.example.demo.exception;

import com.example.demo.common.BasicMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<BasicMessageDto> handleCustomException(CustomException e) {
        ExceptionStatus exceptionStatus = e.getExceptionStatus();
        return new ResponseEntity<>(new BasicMessageDto(exceptionStatus.getMessage()), exceptionStatus.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<BasicMessageDto> handleInputValidationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder message = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            message.append("[");
            message.append(fieldError.getField());
            message.append("](은)는 ");
            message.append(fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(new BasicMessageDto(message.toString()), HttpStatus.BAD_REQUEST);
    }
}
