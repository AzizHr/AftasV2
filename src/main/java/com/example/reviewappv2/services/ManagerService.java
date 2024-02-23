package com.example.reviewappv2.services;

import com.example.reviewappv2.dtos.request.UserNumAndRole;
import com.example.reviewappv2.dtos.response.UserResponse;
import com.example.reviewappv2.exceptions.NotFoundException;

import java.util.List;

public interface ManagerService {

    String activateUserAccount(int userNum) throws NotFoundException;

    String changeUserRole(UserNumAndRole userNumAndRole) throws NotFoundException;

    List<UserResponse> findAllUsers();

}
