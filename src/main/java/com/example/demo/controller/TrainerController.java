package com.example.demo.controller;

import com.example.demo.model.Trainer;
import com.example.demo.service.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trainers")
@CrossOrigin
public class TrainerController {
    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping
    public ResponseEntity<Trainer> addTrainer(@RequestBody Trainer trainer){
        return ResponseEntity.status(HttpStatus.CREATED).body(trainerService.addTrainer(trainer));
    }

}
