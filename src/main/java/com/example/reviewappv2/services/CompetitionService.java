package com.example.reviewappv2.services;

import com.example.reviewappv2.dtos.request.CompetitionRequest;
import com.example.reviewappv2.dtos.response.CompetitionResponse;
import com.example.reviewappv2.exceptions.IllegalCompetitionDateException;
import com.example.reviewappv2.exceptions.IllegalCompetitionEndTimeException;
import com.example.reviewappv2.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompetitionService {
    CompetitionResponse save(CompetitionRequest competitionRequest) throws IllegalCompetitionDateException, IllegalCompetitionEndTimeException;
    CompetitionResponse update(CompetitionRequest competitionRequest, String code) throws NotFoundException, IllegalCompetitionDateException, IllegalCompetitionEndTimeException;
    void delete(String code) throws NotFoundException;
    CompetitionResponse findByCode(String code) throws NotFoundException;
    Page<CompetitionResponse> findAll(Pageable pageable);
}
