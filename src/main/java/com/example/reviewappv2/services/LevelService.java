package com.example.reviewappv2.services;

import com.example.reviewappv2.dtos.request.LevelRequest;
import com.example.reviewappv2.dtos.response.LevelResponse;
import com.example.reviewappv2.exceptions.IllegalLevelPointsException;
import com.example.reviewappv2.exceptions.NotFoundException;

import java.util.List;

public interface LevelService {
    LevelResponse save(LevelRequest levelRequest) throws IllegalLevelPointsException;
    LevelResponse update(LevelRequest levelRequest, int code) throws NotFoundException;
    void delete(int code) throws NotFoundException;
    LevelResponse findByCode(int code) throws NotFoundException;
    List<LevelResponse> findAll();
}
