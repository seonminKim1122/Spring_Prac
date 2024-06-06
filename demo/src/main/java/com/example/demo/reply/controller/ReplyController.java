package com.example.demo.reply.controller;

import com.example.demo.reply.dto.ReplyRequestDto;
import com.example.demo.reply.dto.ReplyResponseDto;
import com.example.demo.reply.service.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/boards/{boardId}/replies")
@AllArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping
    public ReplyResponseDto createReply(@PathVariable Long boardId, @RequestBody ReplyRequestDto replyRequestDto) {
        return replyService.saveReply(replyRequestDto.getContent(), boardId);
    }

    @PutMapping("/{replyId}")
    public ReplyResponseDto updateReply(@PathVariable Long replyId, @RequestBody ReplyRequestDto replyRequestDto) {
        return replyService.updateReply(replyId, replyRequestDto.getContent());
    }
}
