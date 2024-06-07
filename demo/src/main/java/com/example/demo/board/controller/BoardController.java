package com.example.demo.board.controller;

import com.example.demo.board.dto.BoardRequestDto;
import com.example.demo.board.dto.BoardResponseDto;
import com.example.demo.board.service.BoardService;
import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/boards")
public class BoardController {

    private final BoardService boardService;

    /**
     * 유저 연관관계 작성을 위한 임시 코드
     */
    private final UserRepository userRepository;
    private final User user;

    public BoardController(BoardService boardService, UserRepository userRepository) {
        this.boardService = boardService;
        this.userRepository = userRepository;
        this.user = this.userRepository.save(new User("seonmin", "12345678"));
    }

    @PostMapping
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto boardRequestDto) {
        return boardService.saveBoard(user, boardRequestDto.getTitle(), boardRequestDto.getContent());
    }

    @GetMapping("/{id}")
    public BoardResponseDto getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    @PutMapping("/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) throws Exception {
        return boardService.updateBoard(user, id, boardRequestDto.getTitle(), boardRequestDto.getContent());
    }

    @DeleteMapping("/{id}")
    public String deleteBoard(@PathVariable Long id) throws Exception {
        return boardService.deleteBoard(user, id);
    }

    @GetMapping
    public List<BoardResponseDto> getAllBoards() {
        return boardService.getBoards();
    }
}
