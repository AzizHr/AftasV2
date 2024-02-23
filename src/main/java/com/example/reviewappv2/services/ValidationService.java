package com.example.reviewappv2.services;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ValidationService {
    boolean isCompetitionDateValid(LocalDate competitionDate);
    boolean isCompetitionEndTimeValid(LocalTime competitionEndTime, LocalTime competitionStartTime);
}
