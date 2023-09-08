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
    private Integer montacargasSolicitados;

    @Nullable
    private Integer cantidadMontacargas;

    @Nullable
    private Boolean finMontacarga1;

    @Nullable
    private String placa1;

    @Nullable
    private Boolean finMontacarga2;

    @Nullable
    private String placa2;

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

    @Nullable
    private Boolean notificar;

    @Nullable
    private Boolean trabaruedas;
}
