package com.example.reviewappv2.repositories;

import com.example.reviewappv2.models.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface RankingRepository extends JpaRepository<Ranking, Integer> {
    Optional<Ranking> findByCompetitionCodeAndMemberNum(String competitionCode, int memberNum);
    List<Ranking> findTop3ByCompetitionCodeOrderByScoreDesc(String competitionCode);
    List<Ranking> findAllByCompetitionCode(String competitionCode);
}
