package com.example.reviewappv2.services.impl;

import com.example.reviewappv2.dtos.request.LevelRequest;
import com.example.reviewappv2.dtos.response.LevelResponse;
import com.example.reviewappv2.exceptions.IllegalLevelPointsException;
import com.example.reviewappv2.exceptions.NotFoundException;
import com.example.reviewappv2.models.Level;
import com.example.reviewappv2.repositories.LevelRepository;
import com.example.reviewappv2.services.LevelService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;

    @Override
    public LevelResponse save(LevelRequest levelRequest) throws IllegalLevelPointsException {

        List<Level> levels = levelRepository.findAll();
        Level level = modelMapper.map(levelRequest, Level.class);

        for (Level value : levels) {
            if (levelRequest.getPoints() <= value.getPoints()) {
                throw new IllegalLevelPointsException("Points should be greater than " + value.getPoints());
            } else {
                levelRepository.save(level);
            }
        }

        return modelMapper.map(level, LevelResponse.class);
    }

    @Override
    public LevelResponse update(LevelRequest levelRequest, int code) throws NotFoundException {
        if(levelRepository.findByCode(code).isPresent()) {
            Level level = levelRepository.findByCode(code).get();
            return modelMapper.map(levelRepository.save(level), LevelResponse.class);
        }
        throw new NotFoundException("Level not found");
    }

    @Override
    public void delete(int code) throws NotFoundException {
        if(levelRepository.findByCode(code).isPresent()) {
            levelRepository.deleteByCode(code);
        }
        throw new NotFoundException("No level found");
    }

    @Override
    public LevelResponse findByCode(int code) throws NotFoundException {
        if(levelRepository.findByCode(code).isPresent()) {
            Level level = levelRepository.findByCode(code).get();
            return modelMapper.map(level, LevelResponse.class);
        }
        throw new NotFoundException("No level found");
    }

    @Override
    public List<LevelResponse> findAll() {
        List<Level> levels = levelRepository.findAll();
        return levels.stream()
                .map(level -> modelMapper.map(level, LevelResponse.class))
                .collect(Collectors.toList());
    }
}
