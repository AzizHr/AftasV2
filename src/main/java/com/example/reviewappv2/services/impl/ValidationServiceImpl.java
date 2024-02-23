package com.example.reviewappv2.services.impl;

import com.example.reviewappv2.services.ValidationService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ValidationServiceImpl implements ValidationService {

    private final LocalDate currentDate = LocalDate.now();

    @Override
    public boolean isCompetitionDateValid(LocalDate competitionDate) {
        return !competitionDate.isBefore(currentDate);
    }

    @Override
    public boolean isCompetitionEndTimeValid(LocalTime competitionEndTime, LocalTime competitionStartTime) {
        return !(competitionEndTime.isBefore(competitionStartTime) || competitionEndTime == competitionStartTime);
    }
}
