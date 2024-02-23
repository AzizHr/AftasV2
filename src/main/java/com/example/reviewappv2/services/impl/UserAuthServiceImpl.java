package com.example.reviewappv2.services.impl;

import com.example.reviewappv2.dtos.request.LoginRequest;
import com.example.reviewappv2.dtos.request.RegisterRequest;
import com.example.reviewappv2.dtos.response.AuthResponse;
import com.example.reviewappv2.dtos.response.UserResponse;
import com.example.reviewappv2.enums.Role;
import com.example.reviewappv2.models.User;
import com.example.reviewappv2.repositories.UserRepository;
import com.example.reviewappv2.security.JwtService;
import com.example.reviewappv2.security.authenticators.UserAuthenticator;
import com.example.reviewappv2.services.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        User user = userRepository.findUserByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Email or password incorrect"));

        UserAuthenticator userAuthenticator = new UserAuthenticator(user);
        String jwtToken = jwtService.generateToken(userAuthenticator);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUser(modelMapper.map(user, UserResponse.class));
        authResponse.setToken(jwtToken);
        return authResponse;
    }

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        if(userRepository.findUserByUsername(registerRequest.getUsername()).isPresent()) {
            throw new UsernameNotFoundException("Username already exists");
        }
        User user = modelMapper.map(registerRequest, User.class);
        user.setRole(Role.MEMBER);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setActivated(false);
        user.setNationality("MOROCCAN");
        user.setAccessionDate(LocalDate.now());
        userRepository.save(user);
        UserAuthenticator userAuthenticator = new UserAuthenticator(user);
        String jwtToken = jwtService.generateToken(userAuthenticator);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUser(modelMapper.map(user, UserResponse.class));
        authResponse.setToken(jwtToken);
        return authResponse;
    }
}
