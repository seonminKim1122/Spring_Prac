package com.example.demo.recommend.controller;

import com.example.demo.board.dto.BoardResponseDto;
import com.example.demo.recommend.service.RecommendService;
import com.example.demo.reply.dto.ReplyResponseDto;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecommendController {

    private final RecommendService recommendService;

    @PostMapping("/boards/{boardId}/like")
    public ResponseEntity<BoardResponseDto> likeBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long boardId) {
        return recommendService.likeBoard(userDetails.getUser(), boardId);
    }

    @PostMapping("/boards/{boardId}/dislike")
    public ResponseEntity<BoardResponseDto> dislikeBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long boardId) {
        return recommendService.dislikeBoard(userDetails.getUser(), boardId);
    }

    @PostMapping("/replies/{replyId}/like")
    public ResponseEntity<ReplyResponseDto> likeReply(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long replyId) {
        return recommendService.likeReply(userDetails.getUser(), replyId);
    }

    @PostMapping("/replies/{replyId}/dislike")
    public ResponseEntity<ReplyResponseDto> dislikeReply(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long replyId) {
        return recommendService.dislikeReply(userDetails.getUser(), replyId);
    }
}
