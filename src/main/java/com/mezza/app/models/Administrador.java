package com.mezza.app.models;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Administrador {

    @Id
    private Long id;

    @Column (name="email", nullable = false)
    private String email;

    @Column (name = "contrasena", nullable = false)
    private String contrasena;


    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

}