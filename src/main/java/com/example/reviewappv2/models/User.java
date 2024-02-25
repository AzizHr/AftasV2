package com.example.reviewappv2.models;

import com.example.reviewappv2.enums.IdentityDocumentType;
import com.example.reviewappv2.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private int num;
    private String name;
    private String familyName;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean isActivated;
    private LocalDate accessionDate;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocument;
    private String identityNumber;
    @OneToMany(mappedBy = "member")
    private List<Hunting> huntings;
    @OneToMany(mappedBy = "member")
    private List<Ranking> rankings;

}
