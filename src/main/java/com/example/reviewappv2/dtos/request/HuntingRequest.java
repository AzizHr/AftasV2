package com.example.reviewappv2.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class HuntingRequest {
    private int id;
    private int numberOfFish;
    private String competitionCode;
    private int memberNum;
    private String fishName;
}
