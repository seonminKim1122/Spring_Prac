package com.example.demo.board.service;

import com.example.demo.board.domain.Board;
import com.example.demo.board.dto.BoardResponseDto;
import com.example.demo.board.repository.BoardRepository;
import com.example.demo.reply.dto.ReplyResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public BoardResponseDto saveBoard(String title, String content) {
        Board board = new Board(title, content);
        board = boardRepository.save(board);
        return entityToDTO(board);
    }

    @Override
    public BoardResponseDto getBoard(Long id) {
        Board board = getBoardOrElseThrow(id);
        return entityToDTO(board);
    }

    @Override
    public BoardResponseDto updateBoard(Long id, String title, String content) {
        Board board = getBoardOrElseThrow(id);
        board.setTitle(title);
        board.setContent(content);
        board = boardRepository.save(board);
        return entityToDTO(board);
    }

    @Override
    public String deleteBoard(Long id) {
        Board board = getBoardOrElseThrow(id);
        boardRepository.delete(board);
        return "Successfully deleted";
    }

    @Override
    public List<BoardResponseDto> getBoards() {
        List<BoardResponseDto> boards = new ArrayList<>();
        Iterable<Board> boardList = boardRepository.findAll();
        boardList.forEach(board -> boards.add(entityToDTO(board)));
        return boards;
    }

    private Board getBoardOrElseThrow(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Board not found")
        );
        return board;
    }


    private BoardResponseDto entityToDTO(Board board) {
        return BoardResponseDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .views(board.getViews())
                .numOfReplies(board.getNumOfReplies())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .replies(board.getReplies().stream().map(
                        reply -> ReplyResponseDto.builder()
                                .id(reply.getId())
                                .content(reply.getContent())
                                .createdAt(reply.getCreatedAt())
                                .updatedAt(reply.getUpdatedAt())
                                .boardId(board.getId()).build()
                ).collect(Collectors.toList()))
                .build();
    }
}
