package com.ICM.MontacargasAPI.Services;

import com.ICM.MontacargasAPI.Models.CarrilesModel;
import com.ICM.MontacargasAPI.Models.EstadosModel;
import com.ICM.MontacargasAPI.Repositories.CarrilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CarrilesService {
    @Autowired
    private CarrilesRepository carrilesRepository;

    public  CarrilesModel finCargaTotal(Long id){
        Optional<CarrilesModel> existing = carrilesRepository.findById(id);
        LocalTime horaActualPeru = LocalTime.now(ZoneId.of("America/Lima"));
        if (existing.isPresent()){
            CarrilesModel carril = existing.get();
            if (carril.getCantidadMontacargas() == 1 && carril.getFinMontacarga1() || carril.getFinMontacarga2()){
                carril.setFinDeCarga(true);
                carril.setHoraFin(horaActualPeru);
            } else if (carril.getCantidadMontacargas() == 2 && carril.getFinMontacarga1() && carril.getFinMontacarga2() ){
                carril.setFinDeCarga(true);
                carril.setHoraFin(horaActualPeru);
            }
            return carrilesRepository.save(carril);
        }
        return null;
    }

    public CarrilesModel finMontacargas(Long id, int montacarga, boolean presente){
        Optional<CarrilesModel> existing = carrilesRepository.findById(id);
        LocalTime horaActualPeru = LocalTime.now(ZoneId.of("America/Lima"));
        if(existing.isPresent()){
            CarrilesModel carril = existing.get();

            switch (montacarga){
                case 1:
                    carril.setFinMontacarga1(presente);
                    break;
                case 2:
                    carril.setFinMontacarga2(presente);
                    break;
            }
            finCargaTotal(id);
            return carrilesRepository.save(carril);
        }
        return null;
    }


    public CarrilesModel endForklift(Long id, int montacarga, boolean presente) {
        Optional<CarrilesModel> existing = carrilesRepository.findById(id);

        // Configura la zona horaria de Perú
        ZoneId peruZone = ZoneId.of("America/Lima");

        // Obtiene la hora actual en la zona horaria de Perú
        LocalTime horaActualPeru = LocalTime.now(peruZone);

        if (existing.isPresent()) {
            CarrilesModel carril = existing.get();
            if (carril.getEstadosModel().getId() == 3) {
                switch (montacarga) {
                    case 1:
                        if (presente) {
                            if (carril.getCantidadMontacargas() == 1) {
                                carril.setFinMontacarga1(true);
                                carril.setHoraFin(horaActualPeru);
                            } else if (carril.getCantidadMontacargas() == 2) {
                                carril.setFinMontacarga1(true);
                            }
                        } else {
                            if (carril.getCantidadMontacargas() == 1) {
                                carril.setFinMontacarga1(false);
                                carril.setHoraFin(null);
                            } else if (carril.getCantidadMontacargas() == 2) {
                                carril.setFinMontacarga1(false);
                                carril.setHoraFin(null);
                            }
                        }
                        break;

                    case 2:
                        if (presente) {
                            if (carril.getCantidadMontacargas() == 1) {
                                carril.setFinMontacarga1(true);
                                carril.setHoraFin(horaActualPeru);
                            } else if (carril.getCantidadMontacargas() == 2) {
                                carril.setFinMontacarga2(true);
                            }
                        } else {
                            if (carril.getCantidadMontacargas() == 1) {
                                carril.setFinMontacarga1(false);
                                carril.setHoraFin(null);
                            } else if (carril.getCantidadMontacargas() == 2) {
                                carril.setFinMontacarga2(false);
                                carril.setHoraFin(null);
                            }
                        }
                        break;
                }

                return carrilesRepository.save(carril);
            }
        }
        return null;
    }

    public CarrilesModel trabaruedasColocacion(Long id, Boolean colocado){
        Optional<CarrilesModel> existing = carrilesRepository.findById(id);
        if (existing.isPresent()){
            CarrilesModel carril = existing.get();
            carril.setTrabaruedas(colocado);
            return carrilesRepository.save(carril);
        }
        return null;
    }

    public CarrilesModel changeStatus(Long id, Long nuevoEstadoId) {
        Optional<CarrilesModel> existing = carrilesRepository.findById(id);

        if (existing.isPresent()) {
            CarrilesModel carril = existing.get();
            EstadosModel nuevoEstado = new EstadosModel();
            nuevoEstado.setId(nuevoEstadoId);
            // Obtener el estado actual del carril

            if (carril.getEstadosModel().getId() == 1) {
                if (nuevoEstadoId == 1 || nuevoEstadoId == 3) {
                    //no se realizan cambios
                    return null;
                } else if (nuevoEstadoId == 2) {
                    carril.setEstadosModel(nuevoEstado);
                }
            } else if (carril.getEstadosModel().getId() == 2) {
                if (nuevoEstadoId == 1) {
                    carril.setEstadosModel(nuevoEstado);
                } else if (nuevoEstadoId == 2) {
                    return null;
                } else if (nuevoEstadoId == 3 && carril.getTrabaruedas()) {
                    carril.setEstadosModel(nuevoEstado);
                    carril.setCantidadMontacargas(2);
                }
            } else if (carril.getEstadosModel().getId() == 3) {
                if (nuevoEstadoId == 2) {
                    return null;
                } else if (nuevoEstadoId == 1 && !carril.getTrabaruedas() && carril.getFinDeCarga()) {
                    carril.setEstadosModel(nuevoEstado);
                }
            }
            return carrilesRepository.save(carril);
        }
        return null;
    }


    public List<CarrilesModel> getAllLanes(){
        return carrilesRepository.findAll();
    }

    public Optional<CarrilesModel> getLaneById(Long id){
        return carrilesRepository.findById(id);
    }

    public CarrilesModel saveLane(CarrilesModel carrilesModel){
        return carrilesRepository.save(carrilesModel);
    }


    public CarrilesModel editLane(Long id, CarrilesModel carrilesModel){
        Optional<CarrilesModel> existing = carrilesRepository.findById(id);
        if(existing.isPresent()){
            CarrilesModel carril = existing.get();
            carril.setNombre(carrilesModel.getNombre());
            carril.setEstadosModel(carrilesModel.getEstadosModel());
            carril.setCantidadMontacargas(carrilesModel.getCantidadMontacargas());
            carril.setFinMontacarga1(carrilesModel.getFinMontacarga1());
            carril.setFinMontacarga2(carrilesModel.getFinMontacarga2());
            carril.setHoraInicio(carrilesModel.getHoraInicio());
            carril.setHoraFin(carrilesModel.getHoraFin());
            carril.setTrabaruedas(carrilesModel.getTrabaruedas());
            return carrilesRepository.save(carril);
        }
        return null;
    }

    public void deleteLaneById(Long id){
        carrilesRepository.deleteById(id);
    }

    public CarrilesModel startLoading (Long id, CarrilesModel carrilesModel) {
        Optional<CarrilesModel> existing = carrilesRepository.findById(id);
        if (existing.isPresent()) {
            CarrilesModel carril = existing.get();
            carril.setCantidadMontacargas(carrilesModel.getCantidadMontacargas());
            carril.setEstadosModel(carrilesModel.getEstadosModel());

            // Configura la zona horaria de Perú
            ZoneId peruZone = ZoneId.of("America/Lima");

            // Obtiene la hora actual en la zona horaria de Perú
            ZonedDateTime horaInicioPeru = ZonedDateTime.now(peruZone);

            // Extrae la hora local de Perú
            LocalTime horaLocalPeru = horaInicioPeru.toLocalTime();

            // Asigna la hora local de Perú al carril
            carril.setHoraInicio(horaLocalPeru);

            return carrilesRepository.save(carril);
        }
        return null;
    }

    public CarrilesModel endLoading(Long id, CarrilesModel carrilesModel) {
        Optional<CarrilesModel> existing = carrilesRepository.findById(id);
        if (existing.isPresent()) {
            CarrilesModel carril = existing.get();

            // Configurar la zona horaria de Perú (PET - Peru Time)
            ZoneId zonaHorariaPeru = ZoneId.of("America/Lima");

            // Obtener la hora actual en la zona horaria de Perú
            LocalTime horaActualPeru = LocalTime.now(zonaHorariaPeru);

            carril.setHoraFin(horaActualPeru);
            carril.setTrabaruedas(false);
            return carrilesRepository.save(carril);
        }
        return null;
    }

    public CarrilesModel driverDeparture(Long id, CarrilesModel carrilesModel){
        Optional<CarrilesModel> existing = carrilesRepository.findById(id);
        if (existing.isPresent()){
            CarrilesModel carril = existing.get();
            return carrilesRepository.save(carril);
        }
        return null;
    }



    private void resetearValores(CarrilesModel carril) {
        carril.setCantidadMontacargas(null);
        carril.setFinMontacarga1(null);
        carril.setFinMontacarga2(null);
        carril.setHoraInicio(null);
        carril.setHoraFin(null);
        carril.setTrabaruedas(null);
    }



}