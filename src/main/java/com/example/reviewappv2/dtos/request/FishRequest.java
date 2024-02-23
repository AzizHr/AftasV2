package com.example.reviewappv2.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FishRequest {
    private String name;
    private double averageWeight;
    private int levelCode;
}
