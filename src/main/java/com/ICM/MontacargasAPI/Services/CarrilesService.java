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
import java.util.Optional;

@Service
public class CarrilesService {
    @Autowired
    CarrilesRepository carrilesRepository;

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
            carril.setFinAuxiliar(carrilesModel.getFinAuxiliar());
            carril.setSalida(carrilesModel.getSalida());
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
            carril.setFinAuxiliar(carrilesModel.getFinAuxiliar());

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
            carril.setSalida(carrilesModel.getSalida());
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

            if (carril.getEstadosModel().getId() == 1 && nuevoEstado.getId() == 2) {
                // When actual state is 1 (Libre) and "nuevoEstado" is 2 run this condition
                /*
                * When actual state is 1 (Libre) and "nuevoEstado" is 2 run this condition
                * */
                carril.setEstadosModel(nuevoEstado);
                carril.setMontacargasSolicitados(2);
            } else if (carril.getEstadosModel().getId() == 2 && nuevoEstadoId == 1) {
                // Estado 2 -> Estado 1: Restablecer valores
                carril.setEstadosModel(nuevoEstado);
                resetearValores(carril);
            } else if (carril.getEstadosModel().getId() == 3 && carril.getSalida() && nuevoEstadoId == 1) {
                // Estado 3 -> Estado 1: Restablecer valores
                carril.setEstadosModel(nuevoEstado);
                resetearValores(carril);
            } else if (carril.getEstadosModel().getId() == 2 && nuevoEstadoId == 2) {
                // Estado 2 -> Estado 1: Restablecer valores
                carril.setEstadosModel(nuevoEstado);
            } else if (nuevoEstadoId == 404) {
                // Estado 2 -> Estado 1: Restablecer valores
                resetearValores(carril);
                EstadosModel nuevoEstado2 = new EstadosModel();
                nuevoEstado2.setId(1L);
                carril.setEstadosModel(nuevoEstado2);
            }

            return carrilesRepository.save(carril);
        }
        return null;
    }

    private void resetearValores(CarrilesModel carril) {
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
        carril.setTrabaruedas(null);
    }

    // Fin montacargas
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
                                carril.setFinAuxiliar(true);
                                carril.setHoraFin(horaActualPeru);
                            } else if (carril.getCantidadMontacargas() == 2) {
                                carril.setFinMontacarga1(true);
                            }
                        } else {
                            if (carril.getCantidadMontacargas() == 1) {
                                carril.setFinMontacarga1(false);
                                carril.setFinAuxiliar(false);
                                carril.setHoraFin(null);
                            } else if (carril.getCantidadMontacargas() == 2) {
                                carril.setFinMontacarga1(false);
                                carril.setFinAuxiliar(false);
                                carril.setHoraFin(null);
                            }
                        }
                        break;

                    case 2:
                        if (presente) {
                            if (carril.getCantidadMontacargas() == 1) {
                                carril.setFinMontacarga1(true);
                                carril.setFinAuxiliar(true);
                                carril.setHoraFin(horaActualPeru);
                            } else if (carril.getCantidadMontacargas() == 2) {
                                carril.setFinMontacarga2(true);
                            }
                        } else {
                            if (carril.getCantidadMontacargas() == 1) {
                                carril.setFinMontacarga1(false);
                                carril.setFinAuxiliar(false);
                                carril.setHoraFin(null);
                            } else if (carril.getCantidadMontacargas() == 2) {
                                carril.setFinMontacarga2(false);
                                carril.setFinAuxiliar(false);
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

    public CarrilesModel joinForklift(Long id, String placa) {
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

    public CarrilesModel leaveForklift(Long id, String placa) {
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