package com.example.demo.reply.service;

import com.example.demo.board.domain.Board;
import com.example.demo.board.repository.BoardRepository;
import com.example.demo.exception.CustomException;
import com.example.demo.exception.ExceptionStatus;
import com.example.demo.reply.domain.Reply;
import com.example.demo.reply.dto.ReplyResponseDto;
import com.example.demo.reply.repository.ReplyRepository;
import com.example.demo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    @Override
    public ResponseEntity<ReplyResponseDto> saveReply(User user, String content, Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(ExceptionStatus.BOARD_NOT_FOUND));
        Reply reply = new Reply(user, content, board);
        reply = replyRepository.save(reply);
        return new ResponseEntity<>(new ReplyResponseDto(reply), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReplyResponseDto> updateReply(User user, Long replyId, String content) throws Exception {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new CustomException(ExceptionStatus.REPLY_NOT_FOUND));
        if (!reply.getUser().getUsername().equals(user.getUsername())) {
            throw new CustomException(ExceptionStatus.MODIFY_FAILED);
        }

        reply.setContent(content);
        reply = replyRepository.save(reply);
        return new ResponseEntity<>(new ReplyResponseDto(reply), HttpStatus.OK);
    }
}
