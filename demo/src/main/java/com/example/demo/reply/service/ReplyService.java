package com.example.demo.reply.service;

import com.example.demo.reply.dto.ReplyResponseDto;

public interface ReplyService {
    ReplyResponseDto saveReply(String content, Long boardId);
    ReplyResponseDto updateReply(Long replyId, String content);
}
