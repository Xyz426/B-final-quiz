package com.example.demo.service;

import com.example.demo.exception.TraineeNotExistException;
import com.example.demo.model.Trainee;
import com.example.demo.repository.TraineeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        findTraineeById(trainee_id);
        traineeRepository.deleteById(trainee_id);
    }

    public Trainee findTraineeById(Long trainee_id){
        Optional<Trainee> trainee = traineeRepository.findById(trainee_id);
        if(!trainee.isPresent()){
            throw new TraineeNotExistException("trainee Not Found");
        }
        return trainee.get();
    }

    public List<Trainee> findTraineeByGrouped(Boolean grouped) {
        return traineeRepository.findAllByGrouped(grouped);
    }
}
