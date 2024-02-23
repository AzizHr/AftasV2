package com.example.reviewappv2.dtos.response;

import com.example.reviewappv2.dtos.request.CompetitionRequest;
import com.example.reviewappv2.dtos.request.FishRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class HuntingResponse {
    private int id;
    private int numberOfFish;
    private CompetitionRequest competition;
    private UserResponse member;
    private FishRequest fish;
}
