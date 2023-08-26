package com.ICM.MontacargasAPI.Repositories;

import com.ICM.MontacargasAPI.Models.CarrilesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrilesRepository extends JpaRepository<CarrilesModel, Long> {
}
