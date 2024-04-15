package com.ICM.MontacargasAPI.Services;

import com.ICM.MontacargasAPI.Models.EstadosModel;
import com.ICM.MontacargasAPI.Models.SedesModel;
import com.ICM.MontacargasAPI.Repositories.SedesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SedesService {
    @Autowired
    private SedesRepository sedesRepository;

    public List<SedesModel> getAllSedes(){
        return sedesRepository.findAll();
    }

    public Optional<SedesModel> getSedesById(Long id){
        return sedesRepository.findById(id);
    }

    public SedesModel createSedes(SedesModel sedesModel){
        return sedesRepository.save(sedesModel);
    }

    public SedesModel editStatus(Long id, SedesModel sedesModel){
        Optional<SedesModel> data =  sedesRepository.findById(id);
        if(data.isPresent()){
            SedesModel sede = data.get();
            sede.setNombre(sedesModel.getNombre());
            return sedesRepository.save(sede);
        }
        return null;
    }

    public void deleteSedeById(Long id){
        sedesRepository.deleteById(id);
    }
}
