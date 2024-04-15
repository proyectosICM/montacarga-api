package com.ICM.MontacargasAPI.repositories;

import com.ICM.MontacargasAPI.models.EstadosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadosRepository extends JpaRepository<EstadosModel, Long> {
}
