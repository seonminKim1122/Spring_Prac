package com.example.demo.user.service;

import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    String signup(String username, String password);
    String login(String username, String password);
    String logout(HttpServletRequest req);
}
