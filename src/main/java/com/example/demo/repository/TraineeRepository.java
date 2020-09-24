package com.example.demo.repository;

import com.example.demo.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraineeRepository extends JpaRepository<Trainee,Long> {
}
