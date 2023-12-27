package com.ICM.MontacargasAPI.Models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalTime;
/**
 * Represents a lane within the application's context, correlating to a physical lane in a warehouse or similar environment.
 * This class maps to the "Carriles" table and holds information relevant to each lane's operation and status.
 * All columns (except id and name) must be null when the status is "free"
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carriles")
public class CarrilesModel {
    /**
     * Unique identifier for the country record.
     */
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
/*
    @ManyToOne
    @JoinColumn(name = "sede", referencedColumnName = "id", nullable = false)
    private SedesModel sedesModel;
*/

    /**
     * Number of forklifts (montacargas) requested for this lane.
     */
    @Nullable
    private Integer montacargasSolicitados;

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
     * License plate of the first forklift.
     */
    @Nullable
    private String placa1;

    /**
     * Indicates whether the second forklift operation has finished (true) or not (false).
     */
    @Nullable
    private Boolean finMontacarga2;

    /**
     * License plate of the second forklift.
     */
    @Nullable
    private String placa2;

    @Nullable
    private Boolean finAuxiliar;

    @Nullable
    private Boolean salida;

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
/*
    @Nullable
    private Boolean notificar;
*/
    /**
     * Represents if the wheel lock has been placed on the truck
     */
    @Nullable
    private Boolean trabaruedas;
}
