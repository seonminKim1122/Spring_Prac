package com.example.demo.board.service;

import com.example.demo.board.domain.Board;
import com.example.demo.board.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Board saveBoard(String title, String content) {
        Board board = new Board(title, content);
        return boardRepository.save(board);
    }

    @Override
    public Board getBoard(Long id) {
        Board board = getBoardOrElseThrow(id);
        return board;
    }

    @Override
    public Board updateBoard(Long id, String title, String content) {
        Board board = getBoardOrElseThrow(id);
        board.setTitle(title);
        board.setContent(content);
        return boardRepository.save(board);
    }

    @Override
    public String deleteBoard(Long id) {
        Board board = getBoardOrElseThrow(id);
        boardRepository.delete(board);
        return "Successfully deleted";
    }

    @Override
    public List<Board> getBoards() {
        List<Board> boards = new ArrayList<>();
        Iterable<Board> boardList = boardRepository.findAll();
        boardList.forEach(boards::add);
        return boards;
    }

    private Board getBoardOrElseThrow(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Board not found")
        );
        return board;
    }
}
