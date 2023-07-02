package com.mezza.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservaEditDTO {
    private Long id_Reserva;
    private String fecha;
    private String hora;
    private String cant_personas;
}
