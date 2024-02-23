package com.example.reviewappv2.controllers;

import com.example.reviewappv2.dtos.request.UserNumAndRole;
import com.example.reviewappv2.dtos.response.UserResponse;
import com.example.reviewappv2.exceptions.NotFoundException;
import com.example.reviewappv2.services.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manager")
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping("/activate-user-account")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<String> activateUserAccount(@RequestBody int userNum) throws NotFoundException {
        return new ResponseEntity<>(managerService.activateUserAccount(userNum), HttpStatus.OK);
    }

    @PostMapping("/change-user-role")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<String> changeUserRole(@RequestBody UserNumAndRole userNumAndRole) throws NotFoundException {
        return new ResponseEntity<>(managerService.changeUserRole(userNumAndRole), HttpStatus.OK);
    }

    @PostMapping("/users")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<List<UserResponse>> users() {
        return new ResponseEntity<>(managerService.findAllUsers(), HttpStatus.OK);
    }

}
