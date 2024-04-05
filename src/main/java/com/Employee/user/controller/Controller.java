package com.Employee.user.controller;

import com.Employee.user.dto.UserDto;
import com.Employee.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/user")
public class Controller {
    @Autowired
    private UserService userService;
    //Postmapping is to add the product details to database
    @PostMapping//post mapping is to save employee details to database
    public ResponseEntity<UserDto> saveUser(@RequestBody @Valid UserDto userDto)
    {
        UserDto savedUser=userService.addUser(userDto);
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }
    //Getmapping is to get all the product details
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser()
    {
        List<UserDto> allUser=userService.getAllUser();
        return new ResponseEntity<List<UserDto>>(allUser,HttpStatus.OK);
    }
    @GetMapping("/{userId}")//it will get the employee detail when id is given
    public ResponseEntity<UserDto> getUserId(@PathVariable("userId")Long userId)
    {
        UserDto getUser=userService.getUserById(userId);
        return new ResponseEntity<UserDto>(getUser,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")//is to remove the employee details from DB
    public  String deleteUser(@PathVariable("id")Long userId)
    {
        userService.deleteUser(userId);
        return "User is deleted";
    }
    @PutMapping("/{id}")//Update employee details when id is given
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserDto userDto)
    {
        UserDto updatedUser=userService.updateUser(userDto,id);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

}
