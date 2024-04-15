package com.ICM.MontacargasAPI.Models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carriles")
public class CarrilesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    /**
     * Lane name (usually the order number)
     * This field is mandatory and cannot be null.
     */
    @Column(nullable = false)
    private String nombre;

    /**
     * The current state of the lane, represented by an instance of EstadosModel.
     * This field is mandatory and establishes a many-to-one relationship with the Estados table.
     */
    @ManyToOne
    @JoinColumn(name = "estado", referencedColumnName = "id", nullable = false)
    private EstadosModel estadosModel;

    @ManyToOne
    @JoinColumn(name = "sede", referencedColumnName = "id", nullable = false)
    private SedesModel sedesModel;

    /**
     * Represents if the wheel lock has been placed on the truck
     */
    @Nullable
    private Boolean trabaruedas;

    /**
     *Total number of forklifts that will serve the lane.
     */
    @Nullable
    private Integer cantidadMontacargas;

    /**
     * Indicates whether the first forklift operation has finished (true) or not (false).
     */
    @Nullable
    private Boolean finMontacarga1;


    /**
     * Indicates whether the second forklift operation has finished (true) or not (false).
     */
    @Nullable
    private Boolean finMontacarga2;

    /**
     * Indicates whether the second forklift operation has finished (true) or not (false).
     */
    @Nullable
    private Boolean finDeCarga;


    /**
     * Represents the start time of the operation in the lane.
     */
    @Nullable
    private LocalTime horaInicio;

    /**
     * Represents the end time of the operation in the lane.
     */
    @Nullable
    private LocalTime  horaFin;

    /**
     * Represents the total time it took to load

     */
    @Nullable
    private Integer tiempoTotal;


}
