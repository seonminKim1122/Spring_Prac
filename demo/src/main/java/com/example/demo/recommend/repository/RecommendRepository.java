package com.example.demo.recommend.repository;

import com.example.demo.board.domain.Board;
import com.example.demo.recommend.domain.Recommend;
import com.example.demo.reply.domain.Reply;
import com.example.demo.user.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RecommendRepository extends CrudRepository<Recommend, Long> {

    Optional<Recommend> findByUserAndBoardAndIsRecommend(User user, Board board, boolean isRecommend);
    Optional<Recommend> findByUserAndReplyAndIsRecommend(User user, Reply reply, boolean isRecommend);
}
