package com.Employee.user.service;

import com.Employee.user.dto.UserDto;
import com.Employee.user.entity.User1;
import com.Employee.user.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.Employee.user.exception.EmailAlreadyExist;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import java.util.Optional;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceTests {
    private User1 user1;
    @Spy
    private ModelMapper modelMapper=new ModelMapper();
    @Mock
    private EmployeeRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {

         user1 = User1.builder()
                .Id(4L)
                .firstName("john")
                .lastName("doe")
                .email("test@123")
                .build();
    }
    @DisplayName("Test when an Employee with existing email Given throws Email AlreadyExist error")
    @Test
    public void giveEmployee_whenExistingEmail_thenThrowEmailAlreadyExistException() {
        given(userRepository.findByEmail(user1.getEmail()))
                .willReturn(Optional.of(user1));
        UserDto employeeDto = modelMapper.map(user1, UserDto.class);

        assertThrows(EmailAlreadyExist.class,()->userService.addUser(employeeDto));
        verify(userRepository, never()).save(any(User1.class));
    }
    @DisplayName("Test when an Employee Given Create employee in DB")
    @Test
    public void giveEmployee_whenCreateEmployee_thenReturnEmployee() {
        given(userRepository.findByEmail(user1.getEmail()))
                .willReturn(Optional.empty());
        given(userRepository.save(user1)).willReturn(user1);
        UserDto employeeDto = modelMapper.map(user1, UserDto.class);
        UserDto savedemployeeDto = userService.addUser(employeeDto);
        User1 savedemployee = modelMapper.map(savedemployeeDto, User1.class);
        assertThat(savedemployee).isNotNull();
    }
    @DisplayName("Test when an List of Employee Given returns List Of employee")
    @Test
    public void giveEmployeeList_whenGetAllEmployee_thenReturnEmployeeList() {
        User1 employee1 = User1.builder()
                .firstName("Peter")
                .lastName("Hill")
                .email("Peter@123")
                .build();
        given(userRepository.findAll()).willReturn(List.of(employee1,user1));
        List<UserDto> employeeDtos=userService.getAllUser();
        assertThat(employeeDtos).isNotNull();
        assertThat(employeeDtos.size()).isGreaterThan(0);
    }
    @DisplayName("Test when an List of Employee Given returns List Of employee")
    @Test
    public void giveEmployeeId_whenGetEmployee_thenReturnEmployee() {
        given(userRepository.findById(user1.getId())).willReturn(Optional.of(user1));
        UserDto employeeDto=userService.getUserById(user1.getId());
        User1 employee1=modelMapper.map(employeeDto,User1.class);
        assertThat(employee1).isNotNull();
    }
    @DisplayName("Test when an Employee id Given returns Updated employee")
    @Test
    public void givenEmployee_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        given(userRepository.save(user1)).willReturn(user1);
        given(userRepository.findById(user1.getId())).willReturn(Optional.of(user1));
        user1.setEmail("john@gmail.com");
        UserDto employeeDto = modelMapper.map(user1, UserDto.class);
        UserDto savedemployeeDto = userService.updateUser(employeeDto,employeeDto.getId());
        User1 savedemployee = modelMapper.map(savedemployeeDto, User1.class);
        assertThat(savedemployee.getEmail()).isEqualTo("john@gmail.com");
    }



}
