package com.example.demo.repository;

import com.example.demo.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TraineeRepository extends JpaRepository<Trainee,Long> {
    List<Trainee> findAllByGrouped(Boolean grouped);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE Trainee SET grouped = TRUE",nativeQuery = true)
    void updateTraineesGroupStatus();
}
