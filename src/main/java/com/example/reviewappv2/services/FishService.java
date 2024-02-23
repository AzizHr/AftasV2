package com.example.reviewappv2.services;

import com.example.reviewappv2.dtos.request.FishRequest;
import com.example.reviewappv2.dtos.response.FishResponse;
import com.example.reviewappv2.exceptions.NotFoundException;

import java.util.List;

public interface FishService {
    FishResponse save(FishRequest fishRequest) throws NotFoundException;
    FishResponse update(FishRequest fishRequest, String name) throws NotFoundException;
    void delete(String name) throws NotFoundException;
    FishResponse findByName(String name) throws NotFoundException;
    List<FishResponse> findAll();
}
