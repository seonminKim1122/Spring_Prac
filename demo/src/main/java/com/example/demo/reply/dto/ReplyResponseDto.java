package com.example.demo.reply.dto;

import com.example.demo.reply.domain.Reply;
import com.example.demo.user.domain.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ReplyResponseDto {

    private String username;
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long boardId;
    private long numOfLikes;
    private long numOfDislikes;

    public ReplyResponseDto(Reply reply) {
        this.username = reply.getUser().getUsername();
        this.id = reply.getId();
        this.content = reply.getContent();
        this.createdAt = reply.getCreatedAt();
        this.updatedAt = reply.getUpdatedAt();
        this.boardId = reply.getBoard().getId();
        this.numOfLikes = reply.getRecommends().stream().filter(recommend -> recommend.isRecommend()).count();
        this.numOfDislikes = reply.getRecommends().stream().filter(recommend -> !recommend.isRecommend()).count();
    }
}
