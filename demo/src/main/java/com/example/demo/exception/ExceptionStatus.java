package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionStatus {

    DUPLICATE_USERNAME(HttpStatus.CONFLICT, "동일한 username 이 이미 존재합니다"),
    PASSWORD_INCORRECT(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다"),
    MODIFY_FAILED(HttpStatus.UNAUTHORIZED, "수정 권한이 없습니다"),
    DELETE_FAILED(HttpStatus.UNAUTHORIZED, "삭제 권한이 없습니다"),
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다"),
    INVALID_SESSION(HttpStatus.UNAUTHORIZED, "세션이 유효하지 않습니다"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 username 입니다"),
    REPLY_NOT_FOUND(HttpStatus.NO_CONTENT, "존재하지 않는 댓글입니다");

    private HttpStatus status;
    private String message;

    ExceptionStatus(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
