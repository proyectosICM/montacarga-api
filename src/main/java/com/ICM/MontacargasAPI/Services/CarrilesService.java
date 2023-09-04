package com.ICM.MontacargasAPI.Services;

import com.ICM.MontacargasAPI.Models.CarrilesModel;
import com.ICM.MontacargasAPI.Models.EstadosModel;
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

    public CarrilesModel Editar(Long id, CarrilesModel carrilesModel){
        Optional<CarrilesModel> existing = carrilesRepository.findById(id);
        if(existing.isPresent()){
            CarrilesModel carril = existing.get();
            carril.setNombre(carril.getNombre());
            carril.setEstadosModel(carril.getEstadosModel());
            carril.setCantidadMontacargas(carril.getCantidadMontacargas());
            carril.setFinMontacarga1(carril.getFinMontacarga1());
            carril.setFinMontacarga2(carril.getFinMontacarga2());
            carril.setFinAuxiliar(carril.getFinAuxiliar());
            carril.setSalida(carril.getSalida());
            carril.setHoraInicio(carril.getHoraInicio());
            carril.setHoraFin(carril.getHoraFin());
            carril.setNotificar(carrilesModel.getNotificar());
            return carrilesRepository.save(carril);
        }
        return null;
    }

    public CarrilesModel AsingMont(Long id, CarrilesModel carrilesModel){
        Optional<CarrilesModel> existing = carrilesRepository.findById(id);
        if(existing.isPresent()){
            CarrilesModel carril = existing.get();
            carril.setCantidadMontacargas(carrilesModel.getCantidadMontacargas());
            carril.setEstadosModel(carrilesModel.getEstadosModel());
            carril.setHoraInicio(carrilesModel.getHoraInicio());
            return carrilesRepository.save(carril);
        }
        return null;
    }

    public CarrilesModel FinAuxiliar(Long id, CarrilesModel carrilesModel){
        Optional<CarrilesModel> existing = carrilesRepository.findById(id);
        if (existing.isPresent()){
            CarrilesModel carril = existing.get();
            carril.setFinAuxiliar(carrilesModel.getFinAuxiliar());
            carril.setHoraFin(carrilesModel.getHoraFin());
            return carrilesRepository.save(carril);
        }
        return null;
    }

    public CarrilesModel SalidaConductor(Long id, CarrilesModel carrilesModel){
        Optional<CarrilesModel> existing = carrilesRepository.findById(id);
        if (existing.isPresent()){
            CarrilesModel carril = existing.get();
            carril.setSalida(carrilesModel.getSalida());
            return carrilesRepository.save(carril);
        }
        return null;
    }




    // Servicios los sensores
    //Cambiar estado
    public CarrilesModel CambiarEstado(Long id, Long nuevoEstadoId) {
        Optional<CarrilesModel> existing = carrilesRepository.findById(id);
        if (existing.isPresent()) {
            CarrilesModel carril = existing.get();

            EstadosModel nuevoEstado = new EstadosModel();
            nuevoEstado.setId(nuevoEstadoId);

            carril.setEstadosModel(nuevoEstado);
            carril.setNotificar(false);
            if(nuevoEstadoId == 1){
                carril.setSalida(false);
                carril.setMontacargasSolicitados(null);
                carril.setCantidadMontacargas(null);
                carril.setPlaca1(null);
                carril.setPlaca2(null);
                carril.setFinMontacarga1(null);
                carril.setFinMontacarga2(null);
                carril.setFinAuxiliar(null);
                carril.setHoraInicio(null);
                carril.setHoraFin(null);
                carril.setNotificar(null);


            }

            return carrilesRepository.save(carril);
        }
        return null;
    }

    //FIn montacargas
    public CarrilesModel FinMontacargas(Long id, int montacarga){
        Optional<CarrilesModel> existing = carrilesRepository.findById(id);
        if (existing.isPresent()){
            CarrilesModel carril = existing.get();
            if(carril.getEstadosModel().getId() == 3){
                if (montacarga == 1){
                    carril.setFinMontacarga1(true);
                } else if (montacarga == 2) {
                    carril.setFinMontacarga2(true);
                }
            }

            return carrilesRepository.save(carril);
        }
        return null;
    }

    public CarrilesModel UnirseMontacargas(Long id, String placa) {
        Optional<CarrilesModel> existing = carrilesRepository.findById(id);
        return existing.map(carril -> {
            if (carril.getEstadosModel().getId() == 2) {
                int montacargasSolicitados = carril.getMontacargasSolicitados();
                if (montacargasSolicitados == 1 && carril.getPlaca1() == null) {
                    carril.setPlaca1(placa);
                } else if (montacargasSolicitados == 2 && carril.getPlaca1() == null) {
                    carril.setPlaca1(placa);
                } else if (montacargasSolicitados == 2 && carril.getPlaca2() == null) {
                    carril.setPlaca2(placa);
                }
                return carrilesRepository.save(carril);
            }
            return carril;
        }).orElse(null);
    }

    public CarrilesModel DesUnirseMontacargas(Long id, String placa) {
        Optional<CarrilesModel> existing = carrilesRepository.findById(id);
        return existing.map(carril -> {
            if (carril.getEstadosModel().getId() == 2) {
                int montacargasSolicitados = carril.getMontacargasSolicitados();
                if (montacargasSolicitados == 1 && carril.getPlaca1().equals(placa) && carril.getPlaca1() != null) {
                    carril.setPlaca1(null);
                } else if (montacargasSolicitados == 2 && carril.getPlaca1() != null || carril.getPlaca2() != null  ) {
                        if (carril.getPlaca1().equals(placa)) {
                            carril.setPlaca1(null);
                        }else if (carril.getPlaca2().equals(placa)) {
                        carril.setPlaca2(null);
                    }
                    } else if (montacargasSolicitados == 2 && carril.getPlaca1() == null || carril.getPlaca2() != null  ) {
                    if (carril.getPlaca2().equals(placa)) {
                        carril.setPlaca2(null);
                    }
                }else if (montacargasSolicitados == 2 && carril.getPlaca1() != null || carril.getPlaca2() != null  ) {
                    if (carril.getPlaca2().equals(placa)) {
                        carril.setPlaca2(null);
                    }
                }
                return carrilesRepository.save(carril);
            }
            return carril;
        }).orElse(null);
    }


}
