package com.example.demo.board;

import com.example.demo.board.domain.Board;
import com.example.demo.board.repository.BoardRepository;
import com.example.demo.board.service.BoardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;


    @Test
    @Transactional
    void saveBoard() {
        // given
        String title = "title1";
        String content = "content1";

        // when
        Board board = boardService.saveBoard(title, content);

        // then
        Assertions.assertThat(board.getTitle()).isEqualTo(title);
        Assertions.assertThat(board.getContent()).isEqualTo(content);
    }

    @Test
    @Transactional
    void getBoard() {
        // given
        Board savedBoard = boardRepository.save(new Board("title1", "content1"));

        // when
        Board findBoard = boardService.getBoard(savedBoard.getId());

        // then
        Assertions.assertThat(savedBoard.getId()).isEqualTo(findBoard.getId());
        Assertions.assertThat(savedBoard.getTitle()).isEqualTo(findBoard.getTitle());
        Assertions.assertThat(savedBoard.getContent()).isEqualTo(findBoard.getContent());
    }

    @Test
    @Transactional
    void updateBoard() {
        // given
        Board savedBoard = boardRepository.save(new Board("title1", "content1"));

        // when
        Board updatedBoard = boardService.updateBoard(savedBoard.getId(), "title2", "content2");

        // then
        Assertions.assertThat(updatedBoard.getTitle()).isEqualTo("title2");
        Assertions.assertThat(updatedBoard.getContent()).isEqualTo("content2");
    }

    @Test
    @Transactional
    void deleteBoard() {
        // given
        Board savedBoard = boardRepository.save(new Board("title1", "content1"));

        // when
        String result = boardService.deleteBoard(savedBoard.getId());

        // then
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            boardRepository.findById(savedBoard.getId()).orElseThrow(
                    () -> new IllegalArgumentException("Board not found")
            );
        });
    }
}
