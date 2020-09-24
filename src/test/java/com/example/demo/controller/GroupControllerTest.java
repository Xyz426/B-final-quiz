package com.example.demo.controller;

import com.example.demo.model.Group;
import com.example.demo.model.Trainee;
import com.example.demo.model.Trainer;
import com.example.demo.service.GroupService;
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

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GroupController.class)
@AutoConfigureMockMvc
public class GroupControllerTest {
    @MockBean
    GroupService groupService;
    @Autowired
    MockMvc mockMvc;

    private Trainee trainee;
    private Trainer trainer;
    private Group group;
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
        trainer = Trainer.builder()
                .id(1L)
                .name("Foo")
                .build();
        group = Group.builder()
                .id(1L)
                .name("1组")
                .trainees(Collections.singletonList(trainee))
                .trainers(Collections.singletonList(trainer))
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void should_return_group_List_when_request_group() throws Exception {
        when(groupService.findAllGroups()).thenReturn(Collections.singletonList(group));

        mockMvc.perform(get("/groups")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].trainers[0].name",is("Foo")));

        verify(groupService).findAllGroups();
    }
}
