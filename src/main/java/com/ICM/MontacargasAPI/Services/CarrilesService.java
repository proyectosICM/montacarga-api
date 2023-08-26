package com.ICM.MontacargasAPI.Services;

import com.ICM.MontacargasAPI.Models.CarrilesModel;
import com.ICM.MontacargasAPI.Repositories.CarrilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarrilesService {
    @Autowired
    CarrilesRepository carrilesRepository;

    public List<CarrilesModel> GetAll(){
        return carrilesRepository.findAll();
    }

    public Optional<CarrilesModel> GetById(Long id){
        return carrilesRepository.findById(id);
    }

}
