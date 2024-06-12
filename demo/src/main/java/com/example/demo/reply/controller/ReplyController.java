package com.example.demo.reply.controller;

import com.example.demo.reply.dto.ReplyRequestDto;
import com.example.demo.reply.dto.ReplyResponseDto;
import com.example.demo.reply.service.ReplyService;
import com.example.demo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/boards/{boardId}/replies")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping
    public ResponseEntity<ReplyResponseDto> createReply(@RequestAttribute("user") User user, @PathVariable Long boardId, @RequestBody ReplyRequestDto replyRequestDto) {
        return replyService.saveReply(user, replyRequestDto.getContent(), boardId);
    }

    @PutMapping("/{replyId}")
    public ResponseEntity<ReplyResponseDto> updateReply(@RequestAttribute("user") User user, @PathVariable Long replyId, @RequestBody ReplyRequestDto replyRequestDto) throws Exception {
        return replyService.updateReply(user, replyId, replyRequestDto.getContent());
    }
}
