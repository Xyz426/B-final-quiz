package com.example.demo.service;

import com.example.demo.model.Trainer;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public Trainer addTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public List<Trainer> findTrainerByGrouped(Boolean grouped) {
        return trainerRepository.findAllByGrouped(grouped);
    }
}
