package com.example.reviewappv2.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Hunting {

    @Id
    @GeneratedValue
    private int id;
    private int numberOfFish;
    @ManyToOne
    private Competition competition;
    @ManyToOne
    private User member;
    @ManyToOne
    private Fish fish;

}
