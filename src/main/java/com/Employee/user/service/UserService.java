package com.Employee.user.service;

import com.Employee.user.dto.UserDto;

import java.util.List;
//user service for employee controller
public interface UserService {
    UserDto addUser(UserDto userDto);

    UserDto getUserById(Long id);

    List<UserDto> getAllUser();

    UserDto updateUser(UserDto userDto, Long id);

    void deleteUser(Long id);
}