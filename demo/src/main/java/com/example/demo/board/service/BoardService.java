package com.example.demo.board.service;

import com.example.demo.board.domain.Board;

import java.util.List;

public interface BoardService {

    Board saveBoard(String title, String content);
    Board getBoard(Long id);
    Board updateBoard(Long id, String title, String content);
    String deleteBoard(Long id);

    List<Board> getBoards();
}
