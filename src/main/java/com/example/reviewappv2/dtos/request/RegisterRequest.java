package com.example.reviewappv2.dtos.request;

import com.example.reviewappv2.enums.IdentityDocumentType;
import com.example.reviewappv2.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private int num;
    private String name;
    private String familyName;
    private String username;
    private String password;
    private Role role;
    private boolean isActivated;
    private LocalDate accessionDate;
    private String nationality;
    private IdentityDocumentType identityDocument;
    private String identityNumber;

}
