package com.example.demo.common;

import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        if (method.equalsIgnoreCase("GET")) return true; // 단순 조회는 세션 인증 필요 X

        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("loginMember") == null) {
            throw new AuthenticationException("Invalid Session");
        }

        String username = (String) session.getAttribute("loginMember");
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalStateException("User not found")
        );

        request.setAttribute("user", user);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
