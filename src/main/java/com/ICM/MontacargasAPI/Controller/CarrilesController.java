package com.ICM.MontacargasAPI.Controller;

import com.ICM.MontacargasAPI.Models.CarrilesModel;
import com.ICM.MontacargasAPI.Services.CarrilesService;
import org.springframework.core.io.Resource;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/carriles")
public class CarrilesController {
    @Autowired
    CarrilesService carrilesService;

    @GetMapping
    public List<CarrilesModel> getAllLanes(){
        return carrilesService.getAllLanes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarrilesModel> getLaneById(@PathVariable Long id){
        Optional<CarrilesModel> carril = carrilesService.getLaneById(id);
        if (carril.isPresent()){
            return new ResponseEntity<>(carril.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<CarrilesModel> saveLane(@RequestBody CarrilesModel carrilesModel){
        CarrilesModel carril = carrilesService.saveLane(carrilesModel);
        return new ResponseEntity<>(carril, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarrilesModel> editLane(@PathVariable  Long id, @RequestBody CarrilesModel carrilesModel){
        CarrilesModel asignMont = carrilesService.editLane(id, carrilesModel);
        if(asignMont!=null){
            return new ResponseEntity<>(asignMont, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarrilesModel> deleteLaneById(@PathVariable Long id){
        carrilesService.deleteLaneById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/asignarMontacargas/{id}")
    public ResponseEntity<CarrilesModel> startLoading(@PathVariable  Long id, @RequestBody CarrilesModel carrilesModel){
        CarrilesModel asignMont = carrilesService.startLoading(id, carrilesModel);
        if(asignMont!=null){
            return new ResponseEntity<>(asignMont, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //EndPoint para marcar el fin de carga por el auxiliar
    @PutMapping("/finAuxiliar/{id}")
    public ResponseEntity<CarrilesModel> endLoading(@PathVariable Long id, @RequestBody CarrilesModel carrilesModel){
        CarrilesModel finAuxiliar = carrilesService.endLoading(id, carrilesModel);
        if (finAuxiliar!=null){
            return new ResponseEntity<>(finAuxiliar, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/salidaConductor/{id}")
    public ResponseEntity<CarrilesModel> driverDeparture(@PathVariable Long id, @RequestBody CarrilesModel carrilesModel){
        CarrilesModel salidaConductor = carrilesService.driverDeparture(id, carrilesModel);
        if (salidaConductor!=null){
            return new ResponseEntity<>(salidaConductor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/cambiarEstado/{id}/{estado}")
    public ResponseEntity<CarrilesModel> changeStatus(@PathVariable Long id, @PathVariable Long estado){
        CarrilesModel cambiarEstado = carrilesService.changeStatus(id, estado);
        if (cambiarEstado!=null){
            return new ResponseEntity<>(cambiarEstado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/finMontacarga/{id}/{montacarga}/{presente}")
    public ResponseEntity<CarrilesModel> endForklift(@PathVariable Long id, @PathVariable int montacarga, @PathVariable boolean presente){
        CarrilesModel finMontacarga = carrilesService.endForklift(id, montacarga, presente);
        if (finMontacarga!=null){
            return new ResponseEntity<>(finMontacarga, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/unirse/{id}/{placa}")
    public ResponseEntity<CarrilesModel> joinForklift(@PathVariable Long id, @PathVariable String placa){
        CarrilesModel finMontacarga = carrilesService.joinForklift(id, placa);
        if (finMontacarga!=null){
            return new ResponseEntity<>(finMontacarga, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/desunirse/{id}/{placa}")
    public ResponseEntity<CarrilesModel> leaveForklift(@PathVariable Long id, @PathVariable String placa){
        CarrilesModel finMontacarga = carrilesService.leaveForklift(id, placa);
        if (finMontacarga!=null){
            return new ResponseEntity<>(finMontacarga, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
