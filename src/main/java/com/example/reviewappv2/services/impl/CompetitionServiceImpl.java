package com.example.reviewappv2.services.impl;

import com.example.reviewappv2.dtos.request.CompetitionRequest;
import com.example.reviewappv2.dtos.response.CompetitionResponse;
import com.example.reviewappv2.exceptions.IllegalCompetitionDateException;
import com.example.reviewappv2.exceptions.IllegalCompetitionEndTimeException;
import com.example.reviewappv2.exceptions.NotFoundException;
import com.example.reviewappv2.models.Competition;
import com.example.reviewappv2.repositories.CompetitionRepository;
import com.example.reviewappv2.services.CompetitionService;
import com.example.reviewappv2.services.ValidationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;
    private final ValidationService validationService;

    @Override
    public CompetitionResponse save(CompetitionRequest competitionRequest) throws IllegalCompetitionDateException, IllegalCompetitionEndTimeException {
        Competition competition = modelMapper.map(competitionRequest, Competition.class);

        if(validationService.isCompetitionDateValid(competitionRequest.getDate())) {
            if(validationService.isCompetitionEndTimeValid(competitionRequest.getEndTime(), competitionRequest.getStartTime())) {
                competition.setNumberOfParticipants(0);
                String generatedCode = generateCode(competitionRequest.getLocation(), competitionRequest.getDate());

                if (isCodeValid(generatedCode)) {
                    competition.setCode(generatedCode);
                    return modelMapper.map(competitionRepository.save(competition), CompetitionResponse.class);
                }
            }
            throw new IllegalCompetitionEndTimeException("EndTime should be greater than the StartTime");
        }
        throw new IllegalCompetitionDateException("Date should be greater than or equal to current date");
    }

    @Override
    public CompetitionResponse update(CompetitionRequest competitionRequest, String code) throws NotFoundException, IllegalCompetitionDateException, IllegalCompetitionEndTimeException {
        if(competitionRepository.findByCode(code).isPresent()) {
            Competition competition = modelMapper.map(competitionRequest, Competition.class);
            if(validationService.isCompetitionDateValid(competitionRequest.getDate())) {
                if(validationService.isCompetitionEndTimeValid(competitionRequest.getEndTime(), competitionRequest.getStartTime())) {
                    return modelMapper.map(competitionRepository.save(competition), CompetitionResponse.class);
                }
                throw new IllegalCompetitionEndTimeException("EndTime should be greater than the StartTime");
            }
            throw new IllegalCompetitionDateException("Date should be greater than or equal to current date");
        }
        throw new NotFoundException("No competition found");
    }

    @Override
    public void delete(String code) throws NotFoundException {
        if(competitionRepository.findByCode(code).isPresent()) {
            competitionRepository.deleteByCode(code);
        }
        throw new NotFoundException("No competition found");
    }

    @Override
    public CompetitionResponse findByCode(String code) throws NotFoundException {
        if(competitionRepository.findByCode(code).isPresent()) {
            Competition competition = competitionRepository.findByCode(code).get();
            return modelMapper.map(competition, CompetitionResponse.class);
        }
        throw new NotFoundException("No competition found");
    }

    @Override
    public Page<CompetitionResponse> findAll(Pageable pageable) {
        Page<Competition> pageCompetition = competitionRepository.findAll(pageable);

        return new PageImpl<>(
                pageCompetition.getContent().stream()
                        .map(competition -> modelMapper.map(competition, CompetitionResponse.class))
                        .collect(Collectors.toList()),
                pageCompetition.getPageable(),
                pageCompetition.getTotalElements()
        );
    }

    public String generateCode(String location, LocalDate date) {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yy"));
        location = location.substring(0, Math.min(location.length(), 3)).toLowerCase();
        return location + "-" + formattedDate;
    }

    public Boolean isCodeValid(String code) {
        String pattern = "^[a-z]{3}-\\d{2}-\\d{2}-\\d{2}$";
        return Pattern.matches(pattern, code);
    }

}
