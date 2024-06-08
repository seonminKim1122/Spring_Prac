package com.example.demo.user.service;

import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String signup(String username, String password) {
        Optional<User> existUser = userRepository.findByUsername(username);
        if (existUser.isPresent()) {
            return "Username is already in use";
        }
        User user = new User(username, password);
        userRepository.save(user);
        return "Successfully signed up";
    }

    @Override
    public String login(String username, String password) {
        User user = getUser(username);

        if (!user.getPassword().equals(password)) {
            return "Incorrect password";
        }

        return "Successfully logged in";
    }

    private User getUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("Username or password is incorrect")
        );
        return user;
    }
}
