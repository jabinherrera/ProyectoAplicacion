package com.mezza.app.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminEditDTO {

    private Long id;
    private String email;
    private String nombre;
    private String apellido;
}
