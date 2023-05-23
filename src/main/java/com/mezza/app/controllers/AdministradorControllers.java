package com.mezza.app.controllers;
import com.mezza.app.models.*;
import com.mezza.app.services.AdministradorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AdministradorControllers {

    @Autowired
    private AdministradorServices adminServices;

    @PostMapping("registrar")
    public ResponseEntity<?> registrar(@RequestBody AdminRegisterDTO adminRegisterDTO) {
        try {
            adminServices.Registrar(adminRegisterDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Administrador registrado exitosamente");
    }

    @GetMapping("login")
    public ResponseEntity<?> login(@RequestBody AdminLoginDTO adminLoginDTO) {
        try {
            Administrador admin = adminServices.Logear(adminLoginDTO);
            return ResponseEntity.accepted().body(admin);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<?> editar(@RequestBody Administrador adminInfo, @PathVariable Long id) {
        try {
            adminServices.editarAdministrador(adminInfo, id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Datos editados correctamente");

    }


    @DeleteMapping ("eliminar/{email}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            adminServices.eliminarAdministrador(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Administrador eliminado correctamente");

    }
}
