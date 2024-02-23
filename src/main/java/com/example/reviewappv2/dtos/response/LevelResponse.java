package com.example.reviewappv2.dtos.response;

import com.example.reviewappv2.dtos.request.FishRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class LevelResponse {
    private int code;
    private String description;
    private int points;
    private List<FishRequest> fish;
}
