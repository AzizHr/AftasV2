package com.example.reviewappv2.repositories;

import com.example.reviewappv2.models.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface RankingRepository extends JpaRepository<Ranking, Integer> {
    Optional<Ranking> findByCompetitionCodeAndMemberNum(String competitionCode, int memberNum);
    @Query("SELECT r " +
            "FROM Ranking r " +
            "JOIN r.member u " +
            "JOIN r.competition c " +
            "WHERE c.code = :competitionCode " +
            "ORDER BY c.date DESC")
    List<Ranking> findTop3ByCompetitionCodeOrderByDateDesc(String competitionCode);
}
