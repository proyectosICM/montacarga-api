package com.ICM.MontacargasAPI.Repositories;

import com.ICM.MontacargasAPI.Models.EstadosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadosRepository extends JpaRepository<EstadosModel, Long> {
}
