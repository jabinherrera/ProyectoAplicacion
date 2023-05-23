package com.mezza.app.models;

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
