package com.example.demo.board.controller;

import com.example.demo.board.dto.BoardRequestDto;
import com.example.demo.board.dto.BoardResponseDto;
import com.example.demo.board.service.BoardService;
import com.example.demo.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(@RequestAttribute("user") User user, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.saveBoard(user, boardRequestDto.getTitle(), boardRequestDto.getContent());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updateBoard(@RequestAttribute("user") User user, @PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) throws Exception {
        return boardService.updateBoard(user, id, boardRequestDto.getTitle(), boardRequestDto.getContent());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBoard(@RequestAttribute("user") User user, @PathVariable Long id) throws Exception {
        return boardService.deleteBoard(user, id);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getAllBoards() {
        return boardService.getBoards();
    }
}
