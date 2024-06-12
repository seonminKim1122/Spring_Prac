package com.example.demo.reply.service;

import com.example.demo.board.domain.Board;
import com.example.demo.board.repository.BoardRepository;
import com.example.demo.reply.domain.Reply;
import com.example.demo.reply.dto.ReplyResponseDto;
import com.example.demo.reply.repository.ReplyRepository;
import com.example.demo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    @Override
    public ReplyResponseDto saveReply(User user, String content, Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("Board not found"));
        Reply reply = new Reply(user, content, board);
        reply = replyRepository.save(reply);
        return new ReplyResponseDto(reply);
    }

    @Override
    public ReplyResponseDto updateReply(User user, Long replyId, String content) throws Exception {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new IllegalArgumentException("Reply not found"));
        if (!reply.getUser().getUsername().equals(user.getUsername())) {
            throw new Exception("댓글을 수정할 권한이 없습니다.");
        }

        reply.setContent(content);
        reply = replyRepository.save(reply);
        return new ReplyResponseDto(reply);
    }
}
