package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WhiskyController {
    @Autowired
    WhiskyRepository whiskyRepository;

    @Autowired
    DistilleryRepository distilleryRepository;

    //Search path in browser to get whiskies by distillery id: http://localhost:8080/whiskies?distillery_id=1
    //Search path in browser to get whiskies by distillery id: http://localhost:8080/whiskies?distillery_id=1&?age=12
    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskies(
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "distillery_id", required = false) Long distillery_id
    ) {
        if (year != null) {
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByYear(year), HttpStatus.OK);
        }
        if (age != null) {
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByAge(age), HttpStatus.OK);
        }

//        if (region != null) {
//                return new ResponseEntity<>(distilleryRepository.findDistilleriesByRegion(region), HttpStatus.OK);
//        }

        if (distillery_id != null && age != null) {
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByDistilleryId(distillery_id), HttpStatus.OK);
        }

        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies/{id}")
    public ResponseEntity getWhisky(@PathVariable Long id) {
        return new ResponseEntity<>(whiskyRepository.findById(id), HttpStatus.OK);
    }
}
//@GetMapping(value = "/whiskies/age")
//}
