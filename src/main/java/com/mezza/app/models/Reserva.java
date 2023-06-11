package com.mezza.app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Time;
import java.sql.Date;
@Entity
@Table
public class Reserva {

    @Id
    private Long id_Reserva;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "hora", nullable = false)
    private Time hora;

    @Column(name = "cant_personas", nullable = false)
    private Integer cant_personas;

    @Column(name = "nombre", nullable = false)
    private String nombre_cliente;

    @Column(name = "apellido", nullable = false)
    private String apellido_cliente;

    @Column (name = "email", nullable = false)
    private String email_cliente;


}
