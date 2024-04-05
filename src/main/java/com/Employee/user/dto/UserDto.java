package com.Employee.user.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//User dto for employee details to be used
public class UserDto {

    private Long Id;
    @NotEmpty(message = "First Name cannot be empty")
    private  String firstName;
    @NotEmpty(message = "Last Name cannot be empty")
    private  String lastName;
    @NotEmpty(message = "Email cannot be empty")
    @Email
    private  String email;

}

