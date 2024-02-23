package com.example.reviewappv2.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
public class CompetitionRequest {
    private String code;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int numberOfParticipants;
    private String location;
}
