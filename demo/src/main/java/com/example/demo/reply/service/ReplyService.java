package com.example.demo.reply.service;

import com.example.demo.reply.dto.ReplyResponseDto;
import com.example.demo.user.domain.User;

public interface ReplyService {
    ReplyResponseDto saveReply(User user, String content, Long boardId);
    ReplyResponseDto updateReply(User user, Long replyId, String content) throws Exception;
}
