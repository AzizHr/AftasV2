package com.example.reviewappv2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Fish {

    @Id
    private String name;
    private double averageWeight;
    @ManyToOne
    private Level level;
    @OneToMany(mappedBy = "fish")
    private List<Hunting> huntings;

}
