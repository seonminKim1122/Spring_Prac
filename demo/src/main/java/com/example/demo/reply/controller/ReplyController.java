package com.example.demo.reply.controller;

import com.example.demo.reply.dto.ReplyRequestDto;
import com.example.demo.reply.dto.ReplyResponseDto;
import com.example.demo.reply.service.ReplyService;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/boards/{boardId}/replies")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping
    public ResponseEntity<ReplyResponseDto> createReply(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long boardId, @RequestBody ReplyRequestDto replyRequestDto) {
        return replyService.saveReply(userDetails.getUser(), replyRequestDto.getContent(), boardId);
    }

    @PutMapping("/{replyId}")
    public ResponseEntity<ReplyResponseDto> updateReply(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long replyId, @RequestBody ReplyRequestDto replyRequestDto) throws Exception {
        return replyService.updateReply(userDetails.getUser(), replyId, replyRequestDto.getContent());
    }
}
