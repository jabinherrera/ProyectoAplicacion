package com.mezza.app.controllers;
import com.mezza.app.dtos.AdminLoginDTO;
import com.mezza.app.dtos.AdminRegisterDTO;
import com.mezza.app.models.*;
import com.mezza.app.services.AdministradorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AdministradorControllers {

    @Autowired
    private AdministradorServices adminServices;

    @GetMapping("admin/dashboard")
    public String dashboard() {
        return "dashboard-admin";
    }

    @PostMapping("admin/registrar")
    public ResponseEntity<?> registrar(@RequestBody AdminRegisterDTO adminRegisterDTO, Model model) {
        try {
            model.addAttribute("adminRegisterForm", new Administrador());
            adminServices.Registrar(adminRegisterDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Administrador registrado exitosamente");
    }

    @GetMapping("admin/login")
    public String login(@RequestBody AdminLoginDTO adminLoginDTO, Model model) {
        try {
            model.addAttribute("adminLoginForm");
            Administrador admin = adminServices.Logear(adminLoginDTO);
            return "redirect:/dashboard";
        } catch (Exception e) {
            return "ERROR";
        }
    }

    @PutMapping("admin/editar/{id}")
    public ResponseEntity<?> editar(@RequestBody Administrador adminInfo, @PathVariable Long id, Model model) {
        try {
            model.addAttribute("adminEditForm");
            adminServices.editarAdministrador(adminInfo, id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Datos editados correctamente");

    }


    @DeleteMapping ("admin/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            adminServices.eliminarAdministrador(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Administrador eliminado correctamente");

    }
}
