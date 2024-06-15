package com.example.demo.user.service;

import com.example.demo.common.BasicMessageDto;
import com.example.demo.exception.CustomException;
import com.example.demo.exception.ExceptionStatus;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public ResponseEntity<BasicMessageDto> signup(String username, String password) {
        Optional<User> existUser = userRepository.findByUsername(username);
        if (existUser.isPresent()) {
            throw new CustomException(ExceptionStatus.DUPLICATE_USERNAME);
        }

        User user = new User(username, password);
        userRepository.save(user);
        return new ResponseEntity<>(new BasicMessageDto("Successfully signed up"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BasicMessageDto> login(String username, String password) {
        User user = getUser(username);

        if (!user.getPassword().equals(password)) {
            throw new CustomException(ExceptionStatus.PASSWORD_INCORRECT);
        }

        return new ResponseEntity<>(new BasicMessageDto("Successfully logged in"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BasicMessageDto> loginToken(String username, String password, HttpServletResponse response) {
        User user = getUser(username);

        if (!user.getPassword().equals(password)) {
            throw new CustomException(ExceptionStatus.PASSWORD_INCORRECT);
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(username));
        return new ResponseEntity<>(new BasicMessageDto("Successfully logged in"), HttpStatus.OK);
    }


    private User getUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(ExceptionStatus.USER_NOT_FOUND)
        );
        return user;
    }
}
