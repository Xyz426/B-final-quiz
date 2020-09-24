package com.example.demo.controller;

import com.example.demo.model.Trainer;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
                .name("Foo")
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void should_reutn_trainer_when_add_trainer_is_success() throws Exception {
        when(trainerService.addTrainer(trainer)).thenReturn(trainer);

        mockMvc.perform(post("/trainers")
                .content(objectMapper.writeValueAsBytes(trainer))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",is("Foo")));

        verify(trainerService).addTrainer(trainer);
    }
}
