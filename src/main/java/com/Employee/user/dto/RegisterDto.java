package com.Employee.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//Register page Dto
public class RegisterDto {
    private  String name;
    private String username;
    private  String email;
    private  String password;

}
