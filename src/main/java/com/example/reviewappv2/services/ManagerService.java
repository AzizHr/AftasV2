package com.example.reviewappv2.services;

import com.example.reviewappv2.dtos.request.UserNum;
import com.example.reviewappv2.dtos.request.UserNumAndRole;
import com.example.reviewappv2.dtos.response.UserResponse;
import com.example.reviewappv2.exceptions.NotFoundException;

import java.util.List;

public interface ManagerService {

    UserResponse toggleUserAccount(UserNum userNum) throws NotFoundException;

    UserResponse changeUserRole(UserNumAndRole userNumAndRole) throws NotFoundException;

    List<UserResponse> findAllUsers();

}
