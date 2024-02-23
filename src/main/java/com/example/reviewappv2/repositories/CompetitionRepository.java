package com.example.reviewappv2.repositories;

import com.example.reviewappv2.models.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CompetitionRepository extends JpaRepository<Competition, String> {
    Optional<Competition> findByCode(String code);
    void deleteByCode(String code);
}
