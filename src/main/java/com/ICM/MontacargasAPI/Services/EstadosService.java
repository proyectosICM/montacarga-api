package com.ICM.MontacargasAPI.Services;

import com.ICM.MontacargasAPI.Models.EstadosModel;
import com.ICM.MontacargasAPI.Models.SedesModel;
import com.ICM.MontacargasAPI.Repositories.EstadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadosService {
    @Autowired
    private EstadosRepository estadosRepository;

    public List<EstadosModel> getAllStatus(){
        return estadosRepository.findAll();
    }

    public Optional<EstadosModel> getStatusById(Long id){
        return estadosRepository.findById(id);
    }

    public EstadosModel createStatus(EstadosModel estadosModel){
        return estadosRepository.save(estadosModel);
    }

    public EstadosModel editStatus(Long id, EstadosModel estadosModel){
        Optional<EstadosModel> data =  estadosRepository.findById(id);
        if(data.isPresent()){
            EstadosModel estado = data.get();
            estado.setNombre(estadosModel.getNombre());
            return estadosRepository.save(estado);
        }
        return null;
    }

    public void deleteStatusById(Long id){
        estadosRepository.deleteById(id);
    }
}
