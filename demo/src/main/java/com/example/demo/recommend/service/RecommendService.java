package com.example.demo.recommend.service;

import com.example.demo.board.dto.BoardResponseDto;
import com.example.demo.reply.dto.ReplyResponseDto;
import com.example.demo.user.domain.User;

public interface RecommendService {

    BoardResponseDto likeBoard(User user, Long boardId);
    BoardResponseDto dislikeBoard(User user, Long boardId);
    ReplyResponseDto likeReply(User user, Long replyId);
    ReplyResponseDto dislikeReply(User user, Long replyId);

}
