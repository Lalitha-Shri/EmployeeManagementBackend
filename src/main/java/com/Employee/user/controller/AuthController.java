package com.Employee.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Employee.user.dto.JwtAuthResponse;
import com.Employee.user.dto.LoginDto;
import com.Employee.user.dto.RegisterDto;
import com.Employee.user.service.AuthService;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
@CrossOrigin
public class AuthController {
    AuthService authService;
    @PostMapping("/register")
    public String register(@RequestBody RegisterDto registerDto)
    {
       String response1=authService.register(registerDto);
       return response1;
    }
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto)
    {
        JwtAuthResponse jwtAuthResponse=authService.login(loginDto);
        return new ResponseEntity<>(jwtAuthResponse,HttpStatus.OK);
    }
}
