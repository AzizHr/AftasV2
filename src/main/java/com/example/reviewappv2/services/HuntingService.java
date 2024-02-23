package com.example.reviewappv2.services;

import com.example.reviewappv2.dtos.request.HuntingRequest;
import com.example.reviewappv2.dtos.response.HuntingResponse;
import com.example.reviewappv2.exceptions.NotAMemberException;
import com.example.reviewappv2.exceptions.NotFoundException;

import java.util.List;

public interface HuntingService {
    int save(HuntingRequest huntingRequest) throws NotFoundException, NotAMemberException;
    int update(HuntingRequest huntingRequest, int id) throws NotFoundException;
    void delete(int id) throws NotFoundException;
    HuntingResponse findById(int id) throws NotFoundException;
    List<HuntingResponse> findAll();
}
