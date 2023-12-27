package com.ICM.MontacargasAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Represents a location or headquarters within the application's context.
 * This class maps to the "Sedes" table and holds information about different locations or headquarters.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sedes")
public class SedesModel {
    /**
     * Unique identifier for the country record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * Name of headquarters or location
     */
    private String nombre;

}
