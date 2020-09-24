package com.example.demo.repository;

import com.example.demo.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TraineeRepository extends JpaRepository<Trainee,Long> {
    List<Trainee> findAllByGrouped(Boolean grouped);
}
