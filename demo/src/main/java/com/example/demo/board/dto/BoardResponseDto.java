package com.example.demo.board.dto;

import com.example.demo.board.domain.Board;
import com.example.demo.reply.dto.ReplyResponseDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardResponseDto {

    private String username;
    private Long id;
    private String title;
    private String content;
    private int views;
    private int numOfReplies;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ReplyResponseDto> replies;
    private long numOfLikes;
    private long numOfDisLikes;

    public BoardResponseDto(Board board) {
        this.username = board.getUser().getUsername();
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.views = board.getViews();
        this.numOfReplies = board.getReplies().size();
        this.createdAt = board.getCreatedAt();
        this.updatedAt = board.getUpdatedAt();
        this.replies = new ArrayList<>();
        this.replies = board.getReplies().stream().map(ReplyResponseDto::new).collect(Collectors.toList());
        this.numOfLikes = board.getRecommends().stream().filter(recommend -> recommend.isRecommend()).count();
        this.numOfDisLikes = board.getRecommends().stream().filter(recommend -> !recommend.isRecommend()).count();
    }
}
