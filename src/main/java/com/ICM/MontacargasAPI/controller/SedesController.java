package com.ICM.MontacargasAPI.controller;

import com.ICM.MontacargasAPI.models.SedesModel;
import com.ICM.MontacargasAPI.services.SedesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/sedes")
public class SedesController {
    @Autowired
    private SedesService sedesService;

    @GetMapping
    public List<SedesModel> getAllStatus(){return sedesService.getAllSedes();}

    @GetMapping("/{id}")
    public ResponseEntity<SedesModel> getStatusById(@PathVariable Long id){
        Optional<SedesModel> data = sedesService.getSedesById(id);
        if (data.isPresent()){
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<SedesModel> saveLane(@RequestBody SedesModel sedesModel){
        SedesModel data = sedesService.createSedes(sedesModel);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SedesModel> editLane(@PathVariable  Long id, @RequestBody SedesModel sedesModel){
        SedesModel data = sedesService.editStatus(id, sedesModel);
        if(data!=null){
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SedesModel> deleteById(@PathVariable Long id){
        sedesService.deleteSedeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
