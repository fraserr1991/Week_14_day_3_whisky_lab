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
public class DistilleryController {

    @Autowired
    DistilleryRepository distilleryRepository;

    //Search path in browser: http://localhost:8080/distilleries?region=Highland
    //Search path in browser: http://localhost:8080/distilleries?region=Highland&?whiskies
    @GetMapping(value = "/distilleries")
    public ResponseEntity<List<Distillery>> getDistilleries(
            @RequestParam(name = "region", required = false) String region,
            @RequestParam(name = "whiskies", required = false) List<Whisky> whiskies
    ){

        if(region != null && whiskies != null) {
            return new ResponseEntity<>(distilleryRepository.findWhiskiesByWhiskiesInRegion(region,whiskies), HttpStatus.OK);
        }

        if (region != null){
            return new ResponseEntity<>(distilleryRepository.findDistilleriesByRegion(region), HttpStatus.OK);
        }

        return new ResponseEntity<>(distilleryRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/distilleries/{id}")
    public ResponseEntity getDistilleries(@PathVariable Long id){
        return new ResponseEntity<>(distilleryRepository.findById(id), HttpStatus.OK);
    }
}
