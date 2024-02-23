package com.example.reviewappv2.repositories;

import com.example.reviewappv2.models.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LevelRepository extends JpaRepository<Level, Integer> {
    Optional<Level> findByCode(int code);
    void deleteByCode(int code);
}
