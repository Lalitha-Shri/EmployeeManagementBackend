package com.Employee.user.service;

import com.Employee.user.dto.UserDto;
import com.Employee.user.entity.User1;
import com.Employee.user.exception.EmailAlreadyExist;
import com.Employee.user.exception.ResourceNotFoundException;
import com.Employee.user.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
//implements user service interface

public class UserServiceImpl implements UserService{

    private ModelMapper modelMapper;
    private EmployeeRepository userRepository;
    @Override
    public UserDto addUser(UserDto userDto) {
        Optional<User1> optionalUser=userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent())
        {
            throw new EmailAlreadyExist("Email already exist exception");
        }

        User1 user=modelMapper.map(userDto, User1.class);
        User1 savedUser=userRepository.save(user);
        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public UserDto getUserById(Long id) {
        User1 user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee","id",id));
        return modelMapper.map(user,UserDto.class);

    }

    @Override
    public List<UserDto> getAllUser() {
        List<User1> user= userRepository.findAll();
        return user.stream().map(users->modelMapper.map(users,UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        User1 user= userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee","id",id));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        User1 updatedUser=userRepository.save(user);
        return modelMapper.map(updatedUser,UserDto.class);

    }

    @Override
    public void deleteUser(Long id) {
        User1 userDto=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee","id",id));
       userRepository.delete(userDto);

    }
}
