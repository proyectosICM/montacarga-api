package com.ICM.MontacargasAPI.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a state within the context of the application, typically used to describe the state of a lane or similar entity.
 * This class maps to the "States" table and contains information relevant to the attributes of each state.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estados")
public class EstadosModel {
    /**
     * Unique identifier for the country record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * Name that describes the state
     */
    private String nombre;
}
