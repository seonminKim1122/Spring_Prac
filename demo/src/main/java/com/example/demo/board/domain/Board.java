package com.example.demo.board.domain;

import com.example.demo.common.TimeStamp;
import com.example.demo.reply.domain.Reply;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Board extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private int views = 0;

    @OneToMany
    private List<Reply> replies = new ArrayList<>();

    protected Board() {}

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumOfReplies() {
        return replies.size();
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
