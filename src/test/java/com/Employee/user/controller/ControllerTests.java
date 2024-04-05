package com.Employee.user.controller;

import com.Employee.user.dto.UserDto;
import com.Employee.user.entity.User1;
import com.Employee.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class ControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    private ObjectMapper mapper;
    @Spy
    private ModelMapper modelMapper=new ModelMapper();
    private  User1 user1;
    @BeforeEach
    public void setup() {

        user1 = User1.builder()
                .Id(4L)
                .firstName("john")
                .lastName("doe")
                .email("test@123")
                .build();
    }

//    public void givenEmployee_whenCreateEmployee_thenReturnEmployee() throws Exception {
//        UserDto employeeDto = modelMapper.map(user1, UserDto.class);
//
//        given(userService.addUser(any(UserDto.class))).willAnswer(invocation->invocation.getArgument(0));
//        ResultActions response=mockMvc.perform(post("/user")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(user1)));
//        response.andDo(print()).andExpect(status().isCreated())
//                .andExpect(jsonPath("$.firstName",is(employeeDto.getFirstName())))
//                .andExpect(jsonPath("$.lastName",is(employeeDto.getLastName())))
//                .andExpect(jsonPath("$.email",is(employeeDto.getEmail())));
//    }



}
