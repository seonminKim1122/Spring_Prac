package com.example.demo.user.controller;

import com.example.demo.user.dto.UserRequestDto;
import com.example.demo.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody UserRequestDto userRequestDto) {
        return userService.signup(userRequestDto.getUsername(), userRequestDto.getPassword());
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequestDto userRequestDto, HttpServletRequest request) {
        String response = userService.login(userRequestDto.getUsername(), userRequestDto.getPassword());

        // 세션에 유저 정보 저장
        if (response.equals("Successfully logged in")) {
            HttpSession session = request.getSession();
            session.setAttribute("loginMember", userRequestDto.getUsername());
            session.setMaxInactiveInterval(60 * 30);
        }

        return userService.login(userRequestDto.getUsername(), userRequestDto.getPassword());
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
            return "Successfully logged out";
        }

        return "Session Already Expired";
    }
}
