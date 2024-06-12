package com.example.demo.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BasicMessageDto {

    private String message;

    public BasicMessageDto(String message) {
        this.message = message;
    }
}
