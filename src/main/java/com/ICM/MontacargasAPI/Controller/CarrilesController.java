package com.ICM.MontacargasAPI.Controller;

import com.ICM.MontacargasAPI.Models.CarrilesModel;
import com.ICM.MontacargasAPI.Services.CarrilesService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
