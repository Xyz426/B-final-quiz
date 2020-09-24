package com.example.demo.controller;

import com.example.demo.model.Trainee;
import com.example.demo.service.TraineeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TraineeController.class)
@AutoConfigureMockMvc
public class TraineeControllerTest {
    @MockBean
    TraineeService traineeService;
    @Autowired
    MockMvc mockMvc;

    private Trainee trainee;
    private ObjectMapper objectMapper;
    @BeforeEach
    public void beforeEach(){
        trainee = Trainee.builder()
                .name("Foo")
                .office("西安")
                .zoomId("Foo")
                .email("foo@thoughtworks.com")
                .github("foo")
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void should_add_trainee_by_id_when_trainee_id_exist() throws Exception {
        when(traineeService.addTrainee(trainee)).thenReturn(trainee);

        mockMvc.perform(post("/trainees")
                .content(objectMapper.writeValueAsBytes(trainee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",is("Foo")));

        verify(traineeService).addTrainee(trainee);
    }

    @Test
    public void should_return_not_grouped_trainees_when_request_grouped_is_false() throws Exception {
        when(traineeService.findTraineeByGrouped(false)).thenReturn(Collections.singletonList(trainee));

        mockMvc.perform(get("/trainees?grouped=false")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name",is("Foo")));

        verify(traineeService).findTraineeByGrouped(false);
    }
}
