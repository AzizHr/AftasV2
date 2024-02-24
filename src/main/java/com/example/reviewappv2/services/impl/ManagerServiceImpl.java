package com.example.reviewappv2.services.impl;

import com.example.reviewappv2.dtos.request.UserNumAndRole;
import com.example.reviewappv2.dtos.response.UserResponse;
import com.example.reviewappv2.exceptions.AlreadyActivatedException;
import com.example.reviewappv2.exceptions.NotFoundException;
import com.example.reviewappv2.models.User;
import com.example.reviewappv2.repositories.UserRepository;
import com.example.reviewappv2.services.ManagerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserResponse activateUserAccount(int num) throws NotFoundException, AlreadyActivatedException {
        if(userRepository.findByNum(num).isPresent()) {
            User user = userRepository.findByNum(num).get();
            if(user.isActivated()) {
                throw new AlreadyActivatedException("Already activated!");
            } else {
                user.setActivated(true);
                return modelMapper.map(userRepository.save(user), UserResponse.class);
            }
        }
        throw new NotFoundException("No user found");
    }

    @Override
    public UserResponse changeUserRole(UserNumAndRole userNumAndRole) throws NotFoundException {
        if(userRepository.findByNum(userNumAndRole.getUserNum()).isPresent()) {
            User user = userRepository.findByNum(userNumAndRole.getUserNum()).get();
            user.setRole(userNumAndRole.getRole());
            return modelMapper.map(userRepository.save(user), UserResponse.class);
        }
        throw new NotFoundException("No user found");
    }

    @Override
    public List<UserResponse> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
    }
}
