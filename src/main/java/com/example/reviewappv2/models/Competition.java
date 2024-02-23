package com.example.reviewappv2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Competition {

    @Id
    private String code;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int numberOfParticipants;
    private String location;
    @OneToMany(mappedBy = "competition")
    private List<Hunting> huntings;
    @OneToMany(mappedBy = "competition")
    private List<Ranking> rankings;

}
