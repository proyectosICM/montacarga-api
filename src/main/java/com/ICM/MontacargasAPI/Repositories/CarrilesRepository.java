package com.ICM.MontacargasAPI.Repositories;

import com.ICM.MontacargasAPI.Models.CarrilesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository interface for managing lanes (CarrilesModel) in the application.
 * This interface extends JpaRepository to provide basic CRUD operations on CarrilesModel entities.
 */
@Repository
public interface CarrilesRepository extends JpaRepository<CarrilesModel, Long> {
}
