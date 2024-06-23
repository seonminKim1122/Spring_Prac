package com.example.demo.user.domain;

import com.example.demo.common.TimeStamp;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Users")
@Getter
@NoArgsConstructor
@Builder
public class User extends TimeStamp {

    @Id
    private String username;
    @Nullable
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
