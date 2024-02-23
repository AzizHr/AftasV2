package com.example.reviewappv2.repositories;

import com.example.reviewappv2.models.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HuntingRepository extends JpaRepository<Hunting, Integer> {
    Optional<Hunting> findByCompetitionCodeAndMemberNumAndFishName(
            String competitionCode,
            int memberNum, String fishName);
}
