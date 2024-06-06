package com.example.demo.reply.domain;

import com.example.demo.board.domain.Board;
import com.example.demo.common.TimeStamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Reply extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne
    private Board board;

    public Reply(String content, Board board) {
        this.content = content;
        this.board = board;
        this.board.getReplies().add(this);
    }

    public void setContent(String content) {
        this.content = content;
    }
}
