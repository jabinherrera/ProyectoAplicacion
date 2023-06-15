package com.mezza.app.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminLoginDTO {
    private Long id;
    private String email;
    private String contrasena;
}
