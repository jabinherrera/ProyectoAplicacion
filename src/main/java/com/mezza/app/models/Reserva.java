package com.mezza.app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Time;
import java.util.Date;
@Entity

public class Reserva {

    @Id
    private Long id_Reserva;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "hora", nullable = false)
    private Time hora;

    @Column(name = "id_Restaurante", nullable = false)
    private int id_Restaurante;
}
