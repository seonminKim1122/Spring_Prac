package com.example.demo.board.service;

import com.example.demo.board.domain.Board;
import com.example.demo.board.dto.BoardResponseDto;
import com.example.demo.board.repository.BoardRepository;
import com.example.demo.user.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public ResponseEntity<BoardResponseDto> saveBoard(User user, String title, String content) {
        Board board = new Board(user, title, content);
        board = boardRepository.save(board);
        return new ResponseEntity<>(new BoardResponseDto(board), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BoardResponseDto> getBoard(Long id) {
        Board board = getBoardOrElseThrow(id);
        board.addViews();
        board = boardRepository.save(board);
        return new ResponseEntity<>(new BoardResponseDto(board), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BoardResponseDto> updateBoard(User user, Long id, String title, String content) throws Exception {
        Board board = getBoardOrElseThrow(id);
        if (!board.getUser().getUsername().equals(user.getUsername())) {
            throw new Exception("게시글을 수정할 권한이 없습니다.");
        }
        board.setTitle(title);
        board.setContent(content);
        board = boardRepository.save(board);
        return new ResponseEntity<>(new BoardResponseDto(board), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteBoard(User user, Long id) throws Exception {
        Board board = getBoardOrElseThrow(id);
        if (!board.getUser().getUsername().equals(user.getUsername())) {
            throw new Exception("게시글을 삭제할 권한이 없습니다.");
        }
        boardRepository.delete(board);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<BoardResponseDto>> getBoards() {
        List<BoardResponseDto> boards = new ArrayList<>();
        Iterable<Board> boardList = boardRepository.findAll();
        boardList.forEach(board -> boards.add(new BoardResponseDto(board)));
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    private Board getBoardOrElseThrow(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Board not found")
        );
        return board;
    }
}
