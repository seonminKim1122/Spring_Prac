package com.example.demo.recommend.service;

import com.example.demo.board.dto.BoardResponseDto;
import com.example.demo.reply.dto.ReplyResponseDto;
import com.example.demo.user.domain.User;
import org.springframework.http.ResponseEntity;

public interface RecommendService {

    ResponseEntity<BoardResponseDto> likeBoard(User user, Long boardId);
    ResponseEntity<BoardResponseDto> dislikeBoard(User user, Long boardId);
    ResponseEntity<ReplyResponseDto> likeReply(User user, Long replyId);
    ResponseEntity<ReplyResponseDto> dislikeReply(User user, Long replyId);
}
