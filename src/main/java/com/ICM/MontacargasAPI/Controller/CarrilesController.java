package com.ICM.MontacargasAPI.Controller;

import com.ICM.MontacargasAPI.Models.CarrilesModel;
import com.ICM.MontacargasAPI.Services.CarrilesService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/carriles")
public class CarrilesController {
    @Autowired
    CarrilesService carrilesService;

    @GetMapping
    public List<CarrilesModel> GetAll(){
        return carrilesService.GetAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarrilesModel> GetById(@PathVariable Long id){
        Optional<CarrilesModel> carril = carrilesService.GetById(id);
        if (carril.isPresent()){
            return new ResponseEntity<>(carril.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarrilesModel> Editar(@PathVariable  Long id, @RequestBody CarrilesModel carrilesModel){
        CarrilesModel asignMont = carrilesService.Editar(id, carrilesModel);
        if(asignMont!=null){
            return new ResponseEntity<>(asignMont, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/asignarMontacargas/{id}")
    public ResponseEntity<CarrilesModel> AsignMont(@PathVariable  Long id, @RequestBody CarrilesModel carrilesModel){
        CarrilesModel asignMont = carrilesService.AsingMont(id, carrilesModel);
        if(asignMont!=null){
            return new ResponseEntity<>(asignMont, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //EndPoint para marcar el fin de carga por el auxiliar
    @PutMapping("/finAuxiliar/{id}")
    public ResponseEntity<CarrilesModel> FinAuxiliar(@PathVariable Long id, @RequestBody CarrilesModel carrilesModel){
        CarrilesModel finAuxiliar = carrilesService.FinAuxiliar(id, carrilesModel);
        if (finAuxiliar!=null){
            return new ResponseEntity<>(finAuxiliar, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/salidaConductor/{id}")
    public ResponseEntity<CarrilesModel> SalidaConductor(@PathVariable Long id, @RequestBody CarrilesModel carrilesModel){
        CarrilesModel salidaConductor = carrilesService.SalidaConductor(id, carrilesModel);
        if (salidaConductor!=null){
            return new ResponseEntity<>(salidaConductor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
/*
    @PutMapping("/CambiarEstado/{id}")
    public ResponseEntity<CarrilesModel> CambiarEstado(@PathVariable Long id, @RequestBody CarrilesModel carrilesModel){
        CarrilesModel cambiarEstado = carrilesService.CambiarEstado(id, carrilesModel);
        if(cambiarEstado)
    }
*/

    @GetMapping("/hola")
    public String sal() {
        return "Hola p";
    }

    //Controladores pàra los sensiores
    @PutMapping("/cambiarEstado/{id}/{estado}")
    public ResponseEntity<CarrilesModel> CambiarEstado(@PathVariable Long id, @PathVariable Long estado){
        CarrilesModel cambiarEstado = carrilesService.CambiarEstado(id, estado);
        if (cambiarEstado!=null){
            return new ResponseEntity<>(cambiarEstado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/finMontacarga/{id}/{montacarga}")
    public ResponseEntity<CarrilesModel> FinMontacarga(@PathVariable Long id, @PathVariable int montacarga){
        CarrilesModel finMontacarga = carrilesService.FinMontacargas(id, montacarga);
        if (finMontacarga!=null){
            return new ResponseEntity<>(finMontacarga, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
