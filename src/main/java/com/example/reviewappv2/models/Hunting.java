package com.example.reviewappv2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Hunting {

    @Id
    private int id;
    private int numberOfFish;
    @ManyToOne
    private Competition competition;
    @ManyToOne
    private User member;
    @ManyToOne
    private Fish fish;

}
