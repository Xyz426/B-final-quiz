package com.example.demo.controller;

import com.example.demo.model.Trainee;
import com.example.demo.service.TraineeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/trainees")
@CrossOrigin
public class TraineeController {
    private final TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @GetMapping
    public ResponseEntity<List<Trainee>> getNoGroupTrainees(@RequestParam Boolean grouped){
        return ResponseEntity.ok(traineeService.findTraineeByGrouped(grouped));
    }

    @PostMapping
    public ResponseEntity<Trainee> addTrainee(@RequestBody @Valid Trainee trainee){
        return ResponseEntity.status(HttpStatus.CREATED).body(traineeService.addTrainee(trainee));
    }

    @DeleteMapping(path = "/{trainee_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTraineeById(@PathVariable Long trainee_id){
        traineeService.deleteTraineeById(trainee_id);
    }

}
