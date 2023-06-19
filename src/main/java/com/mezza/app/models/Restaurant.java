package com.mezza.app.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Restaurant;

    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "telefono", nullable = false)
    private String telefono;
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "instagram")
    private String instagram;
    @Column(name = "facebook")
    private String facebook;
    @Column(name = "whatsapp")
    private String whatsapp;

}
