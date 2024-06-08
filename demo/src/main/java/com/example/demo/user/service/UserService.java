package com.example.demo.user.service;

public interface UserService {
    String signup(String username, String password);
    String login(String username, String password);
}
