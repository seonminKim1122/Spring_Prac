package com.example.demo.board.controller;

import com.example.demo.board.dto.BoardRequestDto;
import com.example.demo.board.dto.BoardResponseDto;
import com.example.demo.board.service.BoardService;
import com.example.demo.common.BasicMessageDto;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<BoardResponseDto> createBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.saveBoard(userDetails.getUser(), boardRequestDto.getTitle(), boardRequestDto.getContent());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> updateBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) throws Exception {
        return boardService.updateBoard(userDetails.getUser(), id, boardRequestDto.getTitle(), boardRequestDto.getContent());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BasicMessageDto> deleteBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) throws Exception {
        return boardService.deleteBoard(userDetails.getUser(), id);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getAllBoards() {
        return boardService.getBoards();
    }
}
