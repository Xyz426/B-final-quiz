package com.example.demo.service;

import com.example.demo.model.Trainee;
import com.example.demo.repository.TraineeRepository;
import org.springframework.stereotype.Service;

@Service
public class TraineeService {
    private final TraineeRepository traineeRepository;

    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public Trainee addTrainee(Trainee trainee){
        return traineeRepository.save(trainee);
    }

    public void deleteTraineeById(Long trainee_id) {

        traineeRepository.deleteById(trainee_id);
    }
}
