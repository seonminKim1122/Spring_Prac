package com.example.demo.board.service;

import com.example.demo.board.dto.BoardResponseDto;

import java.util.List;

public interface BoardService {

    BoardResponseDto saveBoard(String title, String content);
    BoardResponseDto getBoard(Long id);
    BoardResponseDto updateBoard(Long id, String title, String content);
    String deleteBoard(Long id);

    List<BoardResponseDto> getBoards();
}
