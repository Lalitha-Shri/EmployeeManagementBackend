package com.Employee.user.service;

import com.Employee.user.dto.JwtAuthResponse;
import com.Employee.user.dto.LoginDto;
import com.Employee.user.dto.RegisterDto;
import com.Employee.user.dto.JwtAuthResponse;
import com.Employee.user.dto.LoginDto;
import com.Employee.user.dto.RegisterDto;

public interface AuthService {
    //Service class for Auth controller for spring security
    String register(RegisterDto registerDto);


    JwtAuthResponse login(LoginDto loginDto);
}
