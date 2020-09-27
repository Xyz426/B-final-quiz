package com.example.demo.repository;

import com.example.demo.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer,Long> {
    List<Trainer> findAllByGrouped(Boolean grouped);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE Trainer SET grouped=TRUE WHERE id IN" +
            "(SELECT t.id FROM(SELECT * FROM Trainer LIMIT :num) AS t)",nativeQuery = true)
    void updateTraineesGroupStatus(@Param("num") int num);
}
