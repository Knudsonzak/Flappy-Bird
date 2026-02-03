package com.flappybird.api.repository;

import com.flappybird.api.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findTop10ByOrderByScoreDesc();
    List<Score> findByPlayerNameOrderByScoreDesc(String playerName);
}
