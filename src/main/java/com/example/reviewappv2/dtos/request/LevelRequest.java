package com.example.reviewappv2.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LevelRequest {
    private int code;
    private String description;
    private int points;
}
