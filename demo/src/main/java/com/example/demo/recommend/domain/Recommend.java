package com.example.demo.recommend.domain;

import com.example.demo.board.domain.Board;
import com.example.demo.reply.domain.Reply;
import com.example.demo.user.domain.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recommend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Nullable
    private Board board;

    @ManyToOne
    @Nullable
    private Reply reply;

    @ManyToOne
    private User user;

    private boolean isRecommend; // 추천이면 true, 비추천이면 false
}
