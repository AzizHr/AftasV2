package com.example.reviewappv2.dtos.request;

import com.example.reviewappv2.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserNumAndRole {
    private int userNum;
    private Role role;
}
