package com.example.demo.user.service;

import com.example.demo.common.BasicMessageDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<BasicMessageDto> signup(String username, String password);
    ResponseEntity<BasicMessageDto> login(String username, String password);
}
