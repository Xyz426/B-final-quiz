package com.example.demo.service;

import com.example.demo.exception.TrainerNotExistException;
import com.example.demo.model.Trainer;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void deleteTraineeById(Long trainer_id) {
        findTrainerById(trainer_id);
        trainerRepository.deleteById(trainer_id);
    }

    private Trainer findTrainerById(Long trainer_id) {
        Optional<Trainer> trainer = trainerRepository.findById(trainer_id);
        if(!trainer.isPresent()){
            throw new TrainerNotExistException("trainer Not Found");
        }
        return trainer.get();
    }

    public List<Trainer> findAllTrainers() {
        return trainerRepository.findAll();
    }
}
