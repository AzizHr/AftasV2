package com.example.reviewappv2.services.impl;

import com.example.reviewappv2.dtos.request.FishRequest;
import com.example.reviewappv2.dtos.response.FishResponse;
import com.example.reviewappv2.exceptions.NotFoundException;
import com.example.reviewappv2.models.Fish;
import com.example.reviewappv2.repositories.FishRepository;
import com.example.reviewappv2.repositories.LevelRepository;
import com.example.reviewappv2.services.FishService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FishServiceImpl implements FishService {

    private final FishRepository fishRepository;
    private final LevelRepository levelRepository;
    private final ModelMapper modelMapper;

    @Override
    public FishResponse save(FishRequest fishRequest) throws NotFoundException {
        Fish fish = modelMapper.map(fishRequest, Fish.class);
        fish.setLevel(levelRepository.findByCode(
                fishRequest.getLevelCode())
                .orElseThrow(() -> new NotFoundException("Level not found")));
        return modelMapper.map(fishRepository.save(fish), FishResponse.class);
    }

    @Override
    public FishResponse update(FishRequest fishRequest, String name) throws NotFoundException {
        if(fishRepository.findByName(name).isPresent()) {
            Fish fish = fishRepository.findByName(name).get();
            fish.setLevel(levelRepository.findByCode(
                            fishRequest.getLevelCode())
                    .orElseThrow(() -> new NotFoundException("Level not found")));
            return modelMapper.map(fishRepository.save(fish), FishResponse.class);
        }
        throw new NotFoundException("Fish not found");
    }

    @Override
    public void delete(String name) throws NotFoundException {
        if(fishRepository.findByName(name).isPresent()) {
            fishRepository.deleteByName(name);
        }
        throw new NotFoundException("No fish found");
    }

    @Override
    public FishResponse findByName(String name) throws NotFoundException {
        if(fishRepository.findByName(name).isPresent()) {
            Fish fish = fishRepository.findByName(name).get();
            return modelMapper.map(fish, FishResponse.class);
        }
        throw new NotFoundException("No fish found");
    }

    @Override
    public List<FishResponse> findAll() {
        List<Fish> fishList = fishRepository.findAll();
        return fishList.stream()
                .map(fish -> modelMapper.map(fish, FishResponse.class))
                .collect(Collectors.toList());
    }
}
