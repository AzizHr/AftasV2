package com.example.reviewappv2.services.impl;

import com.example.reviewappv2.dtos.request.HuntingRequest;
import com.example.reviewappv2.dtos.response.HuntingResponse;
import com.example.reviewappv2.exceptions.NotAMemberException;
import com.example.reviewappv2.exceptions.NotFoundException;
import com.example.reviewappv2.models.Fish;
import com.example.reviewappv2.models.Hunting;
import com.example.reviewappv2.models.Ranking;
import com.example.reviewappv2.repositories.*;
import com.example.reviewappv2.services.HuntingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HuntingServiceImpl implements HuntingService {

    private final HuntingRepository huntingRepository;
    private final CompetitionRepository competitionRepository;
    private final UserRepository userRepository;
    private final FishRepository fishRepository;
    private final RankingRepository rankingRepository;
    private final ModelMapper modelMapper;

    @Override
    public int save(HuntingRequest huntingRequest) throws NotFoundException, NotAMemberException {

        Fish fish = fishRepository.findByName(huntingRequest.getFishName())
                .orElseThrow(() -> new NotFoundException("No fish found"));

        int points = fish.getLevel().getPoints();
        int score = huntingRequest.getNumberOfFish() * points;

        if (huntingRepository.findByCompetitionCodeAndMemberNumAndFishName(
                        huntingRequest.getCompetitionCode(),
                        huntingRequest.getMemberNum(),
                        fish.getName()
                ).isPresent()) {

            Hunting existingHunting = huntingRepository
                    .findByCompetitionCodeAndMemberNumAndFishName(
                    huntingRequest.getCompetitionCode(),
                    huntingRequest.getMemberNum(),
                    fish.getName()
            ).get();

            existingHunting.setNumberOfFish(existingHunting.getNumberOfFish() + huntingRequest.getNumberOfFish());
            modelMapper.map(huntingRepository.save(existingHunting), HuntingResponse.class);
        } else {
            if(rankingRepository.findByCompetitionCodeAndMemberNum(huntingRequest.getCompetitionCode(), huntingRequest.getMemberNum()).isPresent()) {
                Hunting newHunting = modelMapper.map(huntingRequest, Hunting.class);
                newHunting.setNumberOfFish(huntingRequest.getNumberOfFish());
                newHunting.setFish(fish);
                newHunting.setMember(userRepository.findByNum(huntingRequest.getMemberNum())
                        .orElseThrow(() -> new NotFoundException("No member found")));
                newHunting.setCompetition(competitionRepository.findByCode(huntingRequest.getCompetitionCode())
                        .orElseThrow(() -> new NotFoundException("No competition found")));
                modelMapper.map(huntingRepository.save(newHunting), HuntingResponse.class);
            } else {
                throw new NotAMemberException("This member is not part of this competition");
            }

        }

        Ranking ranking = rankingRepository.findByCompetitionCodeAndMemberNum(
                huntingRequest.getCompetitionCode(),
                huntingRequest.getMemberNum()).get();

        ranking.setScore(score);
        rankingRepository.save(ranking);
        return score;
    }

    @Override
    public int update(HuntingRequest huntingRequest, int id) throws NotFoundException {

        Fish fish = fishRepository.findByName(huntingRequest.getFishName())
                .orElseThrow(() -> new NotFoundException("No fish found"));

        int points = fish.getLevel().getPoints();
        int score = huntingRequest.getNumberOfFish() * points;

        if (huntingRepository.findById(id).isPresent()) {

            Hunting existingHunting = huntingRepository.findById(id).get();

            existingHunting.setNumberOfFish(existingHunting.getNumberOfFish() + huntingRequest.getNumberOfFish());
            modelMapper.map(huntingRepository.save(existingHunting), HuntingResponse.class);
            Ranking ranking = rankingRepository.findByCompetitionCodeAndMemberNum(
                    huntingRequest.getCompetitionCode(),
                    huntingRequest.getMemberNum()).orElseThrow(() -> new NotFoundException("No ranking found"));

            ranking.setScore(score);
            rankingRepository.save(ranking);
            return score;
        }
        throw new NotFoundException("Hunting not found");
    }

    @Override
    public void delete(int id) throws NotFoundException {
        if(huntingRepository.findById(id).isPresent()) {
            huntingRepository.deleteById(id);
        }
        throw new NotFoundException("No hunting found");
    }

    @Override
    public HuntingResponse findById(int id) throws NotFoundException {
        if(huntingRepository.findById(id).isPresent()) {
            Hunting hunting = huntingRepository.findById(id).get();
            return modelMapper.map(hunting, HuntingResponse.class);
        }
        throw new NotFoundException("No hunting found");
    }

    @Override
    public List<HuntingResponse> findAll() {
        List<Hunting> huntings = huntingRepository.findAll();
        return huntings.stream()
                .map(hunting -> modelMapper.map(hunting, HuntingResponse.class))
                .collect(Collectors.toList());
    }
}
