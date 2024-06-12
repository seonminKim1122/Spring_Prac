package com.example.demo.recommend.controller;

import com.example.demo.board.dto.BoardResponseDto;
import com.example.demo.recommend.service.RecommendService;
import com.example.demo.reply.dto.ReplyResponseDto;
import com.example.demo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecommendController {

    private final RecommendService recommendService;

    @PostMapping("/boards/{boardId}/like")
    public BoardResponseDto likeBoard(@RequestAttribute("user") User user, @PathVariable Long boardId) {
        return recommendService.likeBoard(user, boardId);
    }

    @PostMapping("/boards/{boardId}/dislike")
    public BoardResponseDto dislikeBoard(@RequestAttribute("user") User user, @PathVariable Long boardId) {
        return recommendService.dislikeBoard(user, boardId);
    }

    @PostMapping("/replies/{replyId}/like")
    public ReplyResponseDto likeReply(@RequestAttribute("user") User user, @PathVariable Long replyId) {
        return recommendService.likeReply(user, replyId);
    }

    @PostMapping("/replies/{replyId}/dislike")
    public ReplyResponseDto dislikeReply(@RequestAttribute("user") User user, @PathVariable Long replyId) {
        return recommendService.dislikeReply(user, replyId);
    }
}
