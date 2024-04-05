package com.Employee.user.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.Employee.user.dto.JwtAuthResponse;
import com.Employee.user.dto.LoginDto;
import com.Employee.user.dto.RegisterDto;
import com.Employee.user.entity.Role;
import com.Employee.user.entity.User;
import com.Employee.user.exception.ToDoApiException;
import com.Employee.user.repository.RoleRepository;
import com.Employee.user.repository.UserRepository;
import com.Employee.user.security.JwtUtil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
//implementation of auth service
public class AuthServiceImpl implements AuthService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @Override//get the register details and check if user email exist and throws exception else add the details to dto
    public String register(RegisterDto registerDto) {

        if(userRepository.existsByUsername(registerDto.getUsername())){
        throw new ToDoApiException(HttpStatus.BAD_REQUEST,"UserName already exists");
    }
    if(userRepository.existsByEmail(registerDto.getEmail())){
        throw new ToDoApiException(HttpStatus.BAD_REQUEST,"Email already exists");
    }
        User user=new User();
    user.setName(registerDto.getName());
    user.setUsername(registerDto.getUsername());
    user.setEmail(registerDto.getEmail());
    user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

    Set<Role> roles=new HashSet<>();
    Role userRole=roleRepository.findByName("ROLE_USER");
    roles.add(userRole);
    userRepository.save(user);
    return "User Registered successfully!!";

}

    @Override//to generate jwt token for login credentials given
    public JwtAuthResponse login(LoginDto loginDto) {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtUtil.generate( loginDto.getUsername());
        Optional<User> userOptional= userRepository.findByUsernameOrEmail(loginDto.getUsername(),loginDto.getUsername());
        String role=null;

        if(userOptional.isPresent()){
            User loggedUser=userOptional.get();
            Optional<Role> optionalRole=loggedUser.getRoles().stream().findFirst();
            if(optionalRole.isPresent()){
                Role userRole=optionalRole.get();
                role=userRole.getName();
            }
        }
        JwtAuthResponse jwtAuthResponse=new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        jwtAuthResponse.setRole(role);
        return jwtAuthResponse;
    }
}
