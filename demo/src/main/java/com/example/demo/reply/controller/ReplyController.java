package com.example.demo.reply.controller;

import com.example.demo.reply.dto.ReplyRequestDto;
import com.example.demo.reply.dto.ReplyResponseDto;
import com.example.demo.reply.service.ReplyService;
import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/boards/{boardId}/replies")
public class ReplyController {

    private final ReplyService replyService;
    /*
    유저 연관관계 테스트를 위한 임시 코드
     */
    private final UserRepository userRepository;
    private final User user;

    public ReplyController(ReplyService replyService, UserRepository userRepository) {
        this.replyService = replyService;
        this.userRepository = userRepository;
        this.user = this.userRepository.save(new User("seonmin", "12345678"));
    }

    @PostMapping
    public ReplyResponseDto createReply(@PathVariable Long boardId, @RequestBody ReplyRequestDto replyRequestDto) {
        return replyService.saveReply(user, replyRequestDto.getContent(), boardId);
    }

    @PutMapping("/{replyId}")
    public ReplyResponseDto updateReply(@PathVariable Long replyId, @RequestBody ReplyRequestDto replyRequestDto) throws Exception {
        return replyService.updateReply(user, replyId, replyRequestDto.getContent());
    }
}
