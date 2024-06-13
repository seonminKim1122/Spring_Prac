package com.example.demo.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupDto {

    @NotNull(message = "username 은 필수 입력값입니다")
    @Size(min = 2, max = 20, message = "2 ~ 20 자 사이로 입력해주세요")
    private String username;

    @NotNull(message = "password 는 필수 입력값입니다")
    @Pattern(regexp = "^(?=.*[a-zA-z])(?=.*\\d)(?=.*[!@#$%^&*?_]).{8,20}$",
    message = "8 ~ 20자, 영어 대소문자, 숫자, 특수문자가 각각 최소 1개 이상 포함되어야 합니다")
    private String password;
}
