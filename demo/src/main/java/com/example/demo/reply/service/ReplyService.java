package com.example.demo.reply.service;

import com.example.demo.reply.dto.ReplyResponseDto;
import com.example.demo.user.domain.User;
import org.springframework.http.ResponseEntity;

public interface ReplyService {
    ResponseEntity<ReplyResponseDto> saveReply(User user, String content, Long boardId);
    ResponseEntity<ReplyResponseDto> updateReply(User user, Long replyId, String content) throws Exception;
}
