package com.example.demo.controller;

import com.example.demo.model.Trainee;
import com.example.demo.model.Trainer;
import com.example.demo.service.TraineeService;
import com.example.demo.service.TrainerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrainerController.class)
@AutoConfigureMockMvc
public class TrainerControllerTest {
    @MockBean
    TrainerService trainerService;
    @Autowired
    MockMvc mockMvc;

    private Trainer trainer;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void beforeEach(){
        trainer = Trainer.builder()
                .id(1L)
                .name("Foo")
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void should_return_trainer_when_add_trainer_is_success() throws Exception {
        when(trainerService.addTrainer(trainer)).thenReturn(trainer);

        mockMvc.perform(post("/trainers")
                .content(objectMapper.writeValueAsBytes(trainer))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",is("Foo")));

        verify(trainerService).addTrainer(trainer);
    }

    @Test
    public void should_return_not_grouped_trainers_when_request_grouped_is_false() throws Exception {
        when(trainerService.findTrainerByGrouped(false)).thenReturn(Collections.singletonList(trainer));

        mockMvc.perform(get("/trainers?grouped=false")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name",is("Foo")));

        verify(trainerService).findTrainerByGrouped(false);
    }

    @Test
    public void should_delete_trainer_when_given_trainer_id_exist() throws Exception {
        mockMvc.perform(delete("/trainers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(trainerService).deleteTraineeById(1L);
    }
}
