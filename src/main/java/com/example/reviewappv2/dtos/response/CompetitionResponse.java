package com.example.reviewappv2.dtos.response;

import com.example.reviewappv2.dtos.request.HuntingRequest;
import com.example.reviewappv2.dtos.request.RankingRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class CompetitionResponse {
    private String code;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int numberOfParticipants;
    private String location;
    private List<HuntingRequest> huntings;
    private List<RankingRequest> rankings;
}
