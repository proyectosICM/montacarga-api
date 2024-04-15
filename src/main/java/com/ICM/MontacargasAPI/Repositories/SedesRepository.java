package com.ICM.MontacargasAPI.Repositories;

import com.ICM.MontacargasAPI.Models.SedesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedesRepository extends JpaRepository<SedesModel, Long> {
}
