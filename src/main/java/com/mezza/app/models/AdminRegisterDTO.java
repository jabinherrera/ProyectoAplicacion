package com.mezza.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminRegisterDTO {
    private Long id;
    private String email;
    private String contrasena;
    private String nombre;
    private String apellido;
}
