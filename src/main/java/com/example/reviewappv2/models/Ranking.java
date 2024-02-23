package com.example.reviewappv2.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
//@Table(uniqueConstraints={
//        @UniqueConstraint(columnNames = {"competition_id", "member_id"})
//})
public class Ranking {

    @Id
    private int id;
    @ManyToOne
    private Competition competition;
    @ManyToOne
    private User member;
    private int rank;
    private int score;

}
