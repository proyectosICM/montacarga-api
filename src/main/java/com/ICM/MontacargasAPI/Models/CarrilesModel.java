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

    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "estado", referencedColumnName = "id", nullable = false)
    private EstadosModel estadosModel;
/*
    @ManyToOne
    @JoinColumn(name = "sede", referencedColumnName = "id", nullable = false)
    private SedesModel sedesModel;
*/
    @Nullable
    private Integer cantidadMontacargas;

    @Nullable
    private Boolean finMontacarga;

    @Nullable
    private Boolean finAuxiliar;

    @Nullable
    private Boolean salida;

    @Nullable
    private LocalTime horaInicio;

    @Nullable
    private LocalTime  horaFin;

    @Nullable
    private Integer tiempoTotal;
}
