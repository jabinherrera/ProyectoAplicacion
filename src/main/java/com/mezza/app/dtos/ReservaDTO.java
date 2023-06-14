package com.mezza.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservaDTO {
    private String fecha;
    private String hora;
    private String cant_personas;
    private String nombre_cliente;
    private String apellido_cliente;
    private String email_cliente;
}
