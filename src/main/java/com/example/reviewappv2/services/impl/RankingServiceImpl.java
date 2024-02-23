package com.example.reviewappv2.services.impl;

import com.example.reviewappv2.dtos.request.RankingRequest;
import com.example.reviewappv2.dtos.response.RankingResponse;
import com.example.reviewappv2.exceptions.NotFoundException;
import com.example.reviewappv2.models.Ranking;
import com.example.reviewappv2.repositories.CompetitionRepository;
import com.example.reviewappv2.repositories.RankingRepository;
import com.example.reviewappv2.repositories.UserRepository;
import com.example.reviewappv2.services.RankingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;
    private final CompetitionRepository competitionRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public RankingResponse save(RankingRequest rankingRequest) throws NotFoundException {
        Ranking ranking = modelMapper.map(rankingRequest, Ranking.class);
        ranking.setCompetition(competitionRepository.findByCode(rankingRequest
                .getCompetitionCode()).orElseThrow(() -> new NotFoundException("Competition not found")));
        ranking.setMember(userRepository.findByNum(rankingRequest
                .getMemberNum()).orElseThrow(() -> new NotFoundException("Member not found")));

        ranking.setRank(0);
        ranking.setScore(0);

        return modelMapper.map(rankingRepository.save(ranking), RankingResponse.class);
    }

    @Override
    public RankingResponse update(RankingRequest rankingRequest, int id) throws NotFoundException {
        if(rankingRepository.findById(id).isPresent()) {
            Ranking ranking = rankingRepository.findById(id).get();
            ranking.setCompetition(competitionRepository.findByCode(rankingRequest
                    .getCompetitionCode()).orElseThrow(() -> new NotFoundException("Competition not found")));
            ranking.setMember(userRepository.findByNum(rankingRequest
                    .getMemberNum()).orElseThrow(() -> new NotFoundException("Member not found")));

            return modelMapper.map(rankingRepository.save(ranking), RankingResponse.class);
        }
        throw new NotFoundException("Ranking not found");
    }

    @Override
    public void delete(int id) throws NotFoundException {
        if(rankingRepository.findById(id).isPresent()) {
            rankingRepository.deleteById(id);
        }
        throw new NotFoundException("Ranking not found");
    }

    @Override
    public RankingResponse findById(int id) throws NotFoundException {
        if(rankingRepository.findById(id).isPresent()) {
            Ranking ranking = rankingRepository.findById(id).get();
            return modelMapper.map(ranking, RankingResponse.class);
        }
        throw new NotFoundException("Ranking not found");
    }

    @Override
    public List<RankingResponse> findAll() {
        return Collections.singletonList(modelMapper
                .map(rankingRepository.findAll(), RankingResponse.class));
    }

    @Override
    public List<RankingResponse> findTop3(String competitionCode) {
        List<Ranking> rankings = rankingRepository.findTop3ByCompetitionCodeOrderByDateDesc(competitionCode);
        return rankings.stream()
                .map(ranking -> modelMapper.map(ranking, RankingResponse.class))
                .collect(Collectors.toList());
    }

}
