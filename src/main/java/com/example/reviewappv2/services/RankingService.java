package com.example.reviewappv2.services;

import com.example.reviewappv2.dtos.request.RankingRequest;
import com.example.reviewappv2.dtos.response.RankingResponse;
import com.example.reviewappv2.exceptions.NotFoundException;
import com.example.reviewappv2.models.Ranking;
import com.example.reviewappv2.models.User;

import java.util.List;

public interface RankingService {
    RankingResponse save(RankingRequest rankingRequest) throws NotFoundException;
    RankingResponse update(RankingRequest rankingRequest, int code) throws NotFoundException;
    void delete(int id) throws NotFoundException;
    RankingResponse findById(int id) throws NotFoundException;
    List<RankingResponse> findAll();
    List<RankingResponse> findTop3(String competitionCode);
}
