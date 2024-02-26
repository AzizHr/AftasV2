package com.example.reviewappv2.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Level {

    @Id
    @GeneratedValue
    private int code;
    private String description;
    private int points;
    @OneToMany(mappedBy = "level")
    private List<Fish> fish;

}
