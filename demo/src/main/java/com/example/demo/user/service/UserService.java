package com.example.demo.user.service;

import com.example.demo.common.BasicMessageDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<BasicMessageDto> signup(String username, String password);
    ResponseEntity<BasicMessageDto> login(String username, String password);
    ResponseEntity<BasicMessageDto> loginToken(String username, String password, HttpServletResponse response);
}
