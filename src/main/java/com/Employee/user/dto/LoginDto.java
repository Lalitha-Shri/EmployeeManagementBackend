package com.Employee.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//Login page Dto
public class LoginDto {
    private String username;
    private String password;

}
