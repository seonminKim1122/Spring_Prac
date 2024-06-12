package com.example.demo.board.service;

import com.example.demo.board.dto.BoardResponseDto;
import com.example.demo.user.domain.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BoardService {

    ResponseEntity<BoardResponseDto> saveBoard(User user, String title, String content);
    ResponseEntity<BoardResponseDto> getBoard(Long id);
    ResponseEntity<BoardResponseDto> updateBoard(User user, Long id, String title, String content) throws Exception;
    ResponseEntity<String> deleteBoard(User user, Long id) throws Exception;

    ResponseEntity<List<BoardResponseDto>> getBoards();
}
