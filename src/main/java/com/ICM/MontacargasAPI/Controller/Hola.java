package com.ICM.MontacargasAPI.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Hola {
    @GetMapping
    public String Hola(){
        return "Hola";
    }
}
