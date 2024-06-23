package com.example.demo.oauth.service;

import com.example.demo.common.BasicMessageDto;
import com.example.demo.jwt.JwtUtil;
import com.example.demo.oauth.dto.KakaoUserInfoDto;
import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectUri;
    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String tokenUri;
    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String userInfoUri;

    public ResponseEntity<BasicMessageDto> kakaoLogin(String code, HttpServletResponse response) throws JsonProcessingException {
        log.info(code);
        // 인가 코드로 액세스 토큰 얻기
        String accessToken = getToken(code);

        // 액세스 토큰으로 카카오 API 호출해 사용자 정보 가져오기
        KakaoUserInfoDto kakaoUserInfoDto = getKakaoUserInfo(accessToken);

        // 필요하면 회원가입 시키기
        ifNeedSignup(kakaoUserInfoDto);

        // 사용자 정보 기반으로 JWT 토큰 만들어서 반환
        String token = jwtUtil.createToken(kakaoUserInfoDto.getNickname());
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);

        return new ResponseEntity<>(new BasicMessageDto("Kakao Login Success"), HttpStatus.OK);
    }

    private void ifNeedSignup(KakaoUserInfoDto kakaoUserInfoDto) {
        String nickname = kakaoUserInfoDto.getNickname();
        Optional<User> optional = userRepository.findByUsername(nickname);
        if (optional.isPresent()) return;

        User user = User.builder()
                .username(nickname)
                .build();

        userRepository.save(user);
    }

    private KakaoUserInfoDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        // header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // http 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                userInfoUri,
                HttpMethod.POST,
                kakaoUserInfoRequest,
                String.class);

        // 응답 파싱
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        Long id = jsonNode.get("id").asLong();
        String nickname = jsonNode.get("properties").get("nickname").asText();

        return new KakaoUserInfoDto(id, nickname);
    }

    private String getToken(String code) throws JsonProcessingException {
        // header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // body
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("redirect_uri", redirectUri);
        body.add("code", code);

        // http 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                tokenUri,
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // http 응답 파싱
        String responseBody = responseEntity.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("access_token").asText();
    }
}
