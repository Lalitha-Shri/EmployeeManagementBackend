package com.Employee.user.repository;

import com.Employee.user.dto.UserDto;
import com.Employee.user.entity.User1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest

public class RepositoryTests {
    @Autowired
    private EmployeeRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    private User1 user1;

    @BeforeEach
    public  void setup(){
        user1=User1.builder()
                .firstName("john")
                .lastName("doe")
                .email("test@123")
                .build();
    }

    @DisplayName("Test an Employee getting saved in DB")
    @Test
    public void givenEmployee_whenSaved_returnSavedEmployee(){
        User1 savedUser=userRepository.save(user1);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }
    @DisplayName("Test an Employee getting converted to DTO class")
    @Test
    public void testEntityToDtoConversion() {
        // Create an Order entity


        // Convert the entity to DTO
        UserDto userDto = modelMapper.map(user1, UserDto.class);

        // Assert that the DTO contains the expected values
        assertNotNull(userDto);
        assertEquals("john", userDto.getFirstName());
        assertEquals("doe", userDto.getLastName());
        assertEquals("test@123", userDto.getEmail());

    }

    @DisplayName("Test when given an list of employee return the List of employee")
    @Test
    public void givenEmployeeList_whenFindAll_returnSavedEmployeeList(){
        User1 user2=User1.builder()
                .firstName("Peter")
                .lastName("Hill")
                .email("Peter@123")
                .build();
        userRepository.save(user1);
        userRepository.save(user2);
        List<User1> employeeList=userRepository.findAll();
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);

    }

    @DisplayName("Test when given email return employee detail")
    @Test
    public void givenEmployeeEmail_WhenFindByEmail_ThenReturnEmployee(){
        userRepository.save(user1);
        User1 userDB=userRepository.findByEmail(user1.getEmail()).get();
        assertThat(userDB).isNotNull();
    }

    @DisplayName("Test when given employee ID and employeeInfo update the employee details")
    @Test
    public void givenEmployee_WhenUpdateEmployee_ThenReturnUpdatedEmployee(){
        userRepository.save(user1);
        User1 savedEmployee=userRepository.findById(user1.getId()).get();
        savedEmployee.setEmail("john@gmail.com");
        User1 updatedEmployee=userRepository.save(savedEmployee);
        assertThat(updatedEmployee.getEmail()).isEqualTo("john@gmail.com");
    }

    @DisplayName("Test when given employee details to delete removes info from database")
    @Test
    public void givenEmployee_WhenDeleteEmployee_ThenRemoveEmployee() {
        userRepository.save(user1);
        userRepository.deleteById(user1.getId());
        Optional<User1> optionalEmployee=userRepository.findById(user1.getId());
        assertThat(optionalEmployee).isEmpty();
    }

    @DisplayName("Test when given an employee ID return the Employee from DB")
    @Test
    public void givenEmployeeId_whenFind_returnSavedEmployee(){
        userRepository.save(user1);
        User1 savedemployee=userRepository.findById(user1.getId()).get();
        assertThat(savedemployee).isNotNull();

    }
}
