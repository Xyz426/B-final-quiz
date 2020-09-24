package com.example.demo.repository;

import com.example.demo.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer,Long> {
    List<Trainer> findAllByGrouped(Boolean grouped);
}
