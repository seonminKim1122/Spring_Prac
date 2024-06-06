package com.example.demo.reply.service;

import com.example.demo.board.domain.Board;
import com.example.demo.board.repository.BoardRepository;
import com.example.demo.reply.domain.Reply;
import com.example.demo.reply.dto.ReplyResponseDto;
import com.example.demo.reply.repository.ReplyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    @Override
    public ReplyResponseDto saveReply(String content, Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("Board not found"));
        Reply reply = new Reply(content, board);
        reply = replyRepository.save(reply);

        return ReplyResponseDto.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .updatedAt(reply.getUpdatedAt())
                .boardId(reply.getBoard().getId())
                .build();
    }

    @Override
    public ReplyResponseDto updateReply(Long replyId, String content) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new IllegalArgumentException("Reply not found"));
        reply.setContent(content);
        reply = replyRepository.save(reply);
        return ReplyResponseDto.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .updatedAt(reply.getUpdatedAt())
                .boardId(reply.getBoard().getId())
                .build();
    }
}
