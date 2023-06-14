package com.mezza.app.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime fecha;
    @Column
    private int monto;



}

