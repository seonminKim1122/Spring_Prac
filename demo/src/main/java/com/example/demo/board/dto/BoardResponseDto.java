package com.example.demo.board.dto;

import com.example.demo.board.domain.Board;
import com.example.demo.reply.dto.ReplyResponseDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

}
