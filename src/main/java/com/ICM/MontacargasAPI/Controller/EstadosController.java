package com.ICM.MontacargasAPI.Controller;

import com.ICM.MontacargasAPI.Models.EstadosModel;
import com.ICM.MontacargasAPI.Services.EstadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/estados")
public class EstadosController {
    @Autowired
    private EstadosService estadosService;

    @GetMapping
    public List<EstadosModel> getAllStatus(){return estadosService.getAllStatus();}

    @GetMapping("/{id}")
    public ResponseEntity<EstadosModel> getStatusById(@PathVariable Long id){
        Optional<EstadosModel> data = estadosService.getStatusById(id);
        if (data.isPresent()){
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<EstadosModel> saveLane(@RequestBody EstadosModel estadosModel){
        EstadosModel data = estadosService.createStatus(estadosModel);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadosModel> editLane(@PathVariable  Long id, @RequestBody EstadosModel estadosModel){
        EstadosModel data = estadosService.editStatus(id, estadosModel);
        if(data!=null){
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EstadosModel> deleteById(@PathVariable Long id){
        estadosService.deleteStatusById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
