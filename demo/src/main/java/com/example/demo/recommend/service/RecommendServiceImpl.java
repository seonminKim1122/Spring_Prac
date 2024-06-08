package com.example.demo.recommend.service;

import com.example.demo.board.domain.Board;
import com.example.demo.board.dto.BoardResponseDto;
import com.example.demo.board.repository.BoardRepository;
import com.example.demo.recommend.domain.Recommend;
import com.example.demo.recommend.repository.RecommendRepository;
import com.example.demo.reply.domain.Reply;
import com.example.demo.reply.dto.ReplyResponseDto;
import com.example.demo.reply.repository.ReplyRepository;
import com.example.demo.user.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RecommendServiceImpl implements RecommendService {

    private final RecommendRepository recommendRepository;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Override
    public BoardResponseDto likeBoard(User user, Long boardId) {
        return boardLogic(user, boardId, true);
    }

    @Override
    public BoardResponseDto dislikeBoard(User user, Long boardId) {
        return boardLogic(user, boardId, false);
    }

    @Override
    public ReplyResponseDto likeReply(User user, Long replyId) {
        return replyLogic(user, replyId, true);
    }

    @Override
    public ReplyResponseDto dislikeReply(User user, Long replyId) {
        return replyLogic(user, replyId, false);
    }


    private Board getBoardOrElseThrow(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("Cannot find board"));
    }

    private BoardResponseDto boardLogic(User user, Long boardId, boolean isRecommend) {

        Board board = getBoardOrElseThrow(boardId);
        Optional<Recommend> optional = recommendRepository.findByUserAndBoardAndIsRecommend(user, board, isRecommend);
        if (optional.isPresent()) {
            Recommend recommend = optional.get();
            board.getRecommends().remove(recommend); // 부모에서 먼저 자식을 없애야 한다
            recommendRepository.delete(recommend); // 그렇지 않으면 외래키 제약에 의해 Recommend 를 제거할 수 없다
        } else {
            Recommend recommend = Recommend.builder()
                    .user(user)
                    .board(board)
                    .isRecommend(isRecommend)
                    .build();
            board.getRecommends().add(recommend);
            recommendRepository.save(recommend);
        }
        return new BoardResponseDto(board);
    }

    private Reply getReplyOrElseThrow(Long replyId) {
        return replyRepository.findById(replyId).orElseThrow(() -> new IllegalArgumentException("Cannot find reply"));
    }

    private ReplyResponseDto replyLogic(User user, Long replyId, boolean isRecommend) {

        Reply reply = getReplyOrElseThrow(replyId);
        Optional<Recommend> optional = recommendRepository.findByUserAndReplyAndIsRecommend(user, reply, isRecommend);
        if (optional.isPresent()) {
            Recommend recommend = optional.get();
            reply.getRecommends().remove(recommend); // 부모에서 먼저 자식을 없애야 한다
            recommendRepository.delete(recommend); // 그렇지 않으면 외래키 제약에 의해 Recommend 를 제거할 수 없다
        } else {
            Recommend recommend = Recommend.builder()
                    .user(user)
                    .reply(reply)
                    .isRecommend(isRecommend)
                    .build();
            reply.getRecommends().add(recommend);
            recommendRepository.save(recommend);
        }
        return new ReplyResponseDto(reply);
    }
}
