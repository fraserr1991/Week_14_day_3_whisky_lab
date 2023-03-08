package com.codeclan.example.WhiskyTracker.repositories;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistilleryRepository extends JpaRepository<Distillery, Long> {
    List<Distillery> findDistilleriesByRegion(String region);

    List<Distillery> findWhiskiesByWhiskiesInRegion(String region, List<Whisky> whiskies);

//    List<Distillery> findWhiskiesByDistillery(String name);
}
