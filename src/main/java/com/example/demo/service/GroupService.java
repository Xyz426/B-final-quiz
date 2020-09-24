package com.example.demo.service;

import com.example.demo.exception.TrainerLessTwoException;
import com.example.demo.model.Group;
import com.example.demo.model.Trainee;
import com.example.demo.model.Trainer;
import com.example.demo.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final TrainerService trainerService;
    private final TraineeService traineeService;

    public GroupService(GroupRepository groupRepository, TrainerService trainerService, TraineeService traineeService) {
        this.groupRepository = groupRepository;
        this.trainerService = trainerService;
        this.traineeService = traineeService;
    }

    public List<Group> findAllGroups() {
        return groupRepository.findAll();
    }

    public List<Group> autoGrouping() {
        List<Trainer> trainers = trainerService.findAllTrainers();
        if (trainers.size() < 2) {
            throw new TrainerLessTwoException("Trainer is less two");
        }
        List<Trainee> trainees = traineeService.findAllTrainees();

        if (trainers.size() % 2 != 0) {
            trainers.remove(trainees.size() - 1);
        }
        Collections.shuffle(trainees);
        Collections.shuffle(trainers);

        List<Group> groups = new ArrayList<>();
        for (int i = 0; i < trainers.size() / 2; i++) {
            groups.add(Group.builder().name(i+1+"组")
                    .trainers(new ArrayList<>())
                    .trainees(new ArrayList<>())
                    .build());
        }

        return grouping(groups, trainers, trainees);
    }

    public List<Group> grouping(List<Group> groups, List<Trainer> trainers
            , List<Trainee> trainees) {

        for (int i = 0; i < trainers.size()/2; i++) {
            groups.get(i).getTrainers().add(trainers.get(i));
            groups.get(i).getTrainers().add(trainers.get(trainers.size()-1));
        }

        for (int i = 0; i < trainees.size(); i++) {
            groups.get(i % groups.size()).getTrainees().add(trainees.get(i));
        }

        groups.stream().forEach(group -> {
            groupRepository.save(group);
        });
        return groups;
    }
}
