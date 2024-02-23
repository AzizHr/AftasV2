package com.example.reviewappv2.repositories;

import com.example.reviewappv2.models.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FishRepository extends JpaRepository<Fish, String> {
    Optional<Fish> findByName(String name);
    void deleteByName(String name);
}
