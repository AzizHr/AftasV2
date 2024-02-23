package com.example.reviewappv2.dtos.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RankingResponse {
    private int id;
    private CompetitionResponse competition;
    private UserResponse member;
    private int rank;
    private int score;
}
