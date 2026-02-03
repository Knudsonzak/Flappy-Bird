package com.flappybird.api.controller;

import com.flappybird.api.model.Score;
import com.flappybird.api.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ScoreController {
    
    @Autowired
    private ScoreRepository scoreRepository;
    
    @GetMapping("/")
    public ResponseEntity<Map<String, String>> home() {
        return ResponseEntity.ok(Map.of(
            "message", "Flappy Bird API",
            "version", "1.0",
            "endpoints", "/api/scores, /api/leaderboard, /api/player/{name}"
        ));
    }
    
    @PostMapping("/scores")
    public ResponseEntity<Score> submitScore(@RequestBody Score score) {
        if (score.getPlayerName() == null || score.getScore() == null) {
            return ResponseEntity.badRequest().build();
        }
        Score savedScore = scoreRepository.save(score);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedScore);
    }
    
    @GetMapping("/scores")
    public ResponseEntity<List<Score>> getAllScores() {
        List<Score> scores = scoreRepository.findAll();
        return ResponseEntity.ok(scores);
    }
    
    @GetMapping("/leaderboard")
    public ResponseEntity<List<Score>> getLeaderboard() {
        List<Score> topScores = scoreRepository.findTop10ByOrderByScoreDesc();
        return ResponseEntity.ok(topScores);
    }
    
    @GetMapping("/player/{name}")
    public ResponseEntity<List<Score>> getPlayerScores(@PathVariable String name) {
        List<Score> playerScores = scoreRepository.findByPlayerNameOrderByScoreDesc(name);
        return ResponseEntity.ok(playerScores);
    }
    
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        long totalGames = scoreRepository.count();
        List<Score> topScores = scoreRepository.findTop10ByOrderByScoreDesc();
        Integer highScore = topScores.isEmpty() ? 0 : topScores.get(0).getScore();
        
        return ResponseEntity.ok(Map.of(
            "totalGames", totalGames,
            "highScore", highScore,
            "topPlayers", topScores.size()
        ));
    }
}
