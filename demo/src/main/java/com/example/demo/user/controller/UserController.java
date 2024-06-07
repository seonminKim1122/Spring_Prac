package com.example.demo.user.controller;

import com.example.demo.user.dto.UserRequestDto;
import com.example.demo.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody UserRequestDto userRequestDto) {
        return userService.signup(userRequestDto.getUsername(), userRequestDto.getPassword());
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequestDto userRequestDto) {
        return userService.login(userRequestDto.getUsername(), userRequestDto.getPassword());
    }
}
