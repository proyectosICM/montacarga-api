package com.ICM.MontacargasAPI.repositories;

import com.ICM.MontacargasAPI.models.SedesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedesRepository extends JpaRepository<SedesModel, Long> {
}
