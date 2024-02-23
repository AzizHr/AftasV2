package com.example.reviewappv2.dtos.response;

import com.example.reviewappv2.dtos.request.HuntingRequest;
import com.example.reviewappv2.dtos.request.LevelRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class FishResponse {
    private String name;
    private double averageWeight;
    private LevelRequest level;
    private List<HuntingRequest> huntings;
}
