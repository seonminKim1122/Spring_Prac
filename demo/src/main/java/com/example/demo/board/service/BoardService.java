package com.example.demo.board.service;

import com.example.demo.board.dto.BoardResponseDto;
import com.example.demo.user.domain.User;

import java.util.List;

public interface BoardService {

    BoardResponseDto saveBoard(User user, String title, String content);
    BoardResponseDto getBoard(Long id);
    BoardResponseDto updateBoard(User user, Long id, String title, String content) throws Exception;
    String deleteBoard(User user, Long id) throws Exception;

    List<BoardResponseDto> getBoards();
}
