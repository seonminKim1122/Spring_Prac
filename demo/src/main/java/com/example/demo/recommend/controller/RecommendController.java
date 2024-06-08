package com.example.demo.recommend.controller;

import com.example.demo.board.dto.BoardResponseDto;
import com.example.demo.recommend.service.RecommendService;
import com.example.demo.reply.dto.ReplyResponseDto;
import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommendController {

    private final RecommendService recommendService;

    /**
     * 유저 연관관계 작성을 위한 임시 코드
     */
    private final UserRepository userRepository;
    private final User user;

    public RecommendController(RecommendService recommendService, UserRepository userRepository) {
        this.recommendService = recommendService;
        this.userRepository = userRepository;
        this.user = this.userRepository.save(new User("seonmin", "12345678"));
    }

    @PostMapping("/boards/{boardId}/like")
    public BoardResponseDto likeBoard(@PathVariable Long boardId) {
        return recommendService.likeBoard(user, boardId);
    }

    @PostMapping("/boards/{boardId}/dislike")
    public BoardResponseDto dislikeBoard(@PathVariable Long boardId) {
        return recommendService.dislikeBoard(user, boardId);
    }

    @PostMapping("/replies/{replyId}/like")
    public ReplyResponseDto likeReply(@PathVariable Long replyId) {
        return recommendService.likeReply(user, replyId);
    }

    @PostMapping("/replies/{replyId}/dislike")
    public ReplyResponseDto dislikeReply(@PathVariable Long replyId) {
        return recommendService.dislikeReply(user, replyId);
    }
}
