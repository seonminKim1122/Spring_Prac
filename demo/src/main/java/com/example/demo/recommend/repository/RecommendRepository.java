package com.example.demo.recommend.repository;

import com.example.demo.recommend.domain.Recommend;
import org.springframework.data.repository.CrudRepository;

public interface RecommendRepository extends CrudRepository<Recommend, Long> {
}
