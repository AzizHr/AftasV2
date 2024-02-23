package com.example.reviewappv2.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RankingRequest {
    private int id;
    private String competitionCode;
    private int memberNum;
    private int rank;
    private int score;
}
