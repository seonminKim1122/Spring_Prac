package com.example.demo.board.controller;

import com.example.demo.board.dto.BoardRequestDto;
import com.example.demo.board.dto.BoardResponseDto;
import com.example.demo.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto boardRequestDto) {
        return boardService.saveBoard(boardRequestDto.getTitle(), boardRequestDto.getContent());
    }

    @GetMapping("/{id}")
    public BoardResponseDto getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    @PutMapping("/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.updateBoard(id, boardRequestDto.getTitle(), boardRequestDto.getContent());
    }

    @DeleteMapping("/{id}")
    public String deleteBoard(@PathVariable Long id) {
        return boardService.deleteBoard(id);
    }

    @GetMapping
    public List<BoardResponseDto> getAllBoards() {
        return boardService.getBoards();
    }
}
