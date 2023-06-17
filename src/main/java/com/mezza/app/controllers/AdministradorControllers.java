package com.mezza.app.controllers;
import com.mezza.app.dtos.AdminLoginDTO;
import com.mezza.app.dtos.AdminRegisterDTO;
import com.mezza.app.dtos.ReservaDTO;
import com.mezza.app.models.*;
import com.mezza.app.repositories.AdministradorRepository;
import com.mezza.app.services.AdministradorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@Controller
public class AdministradorControllers {

    @Autowired
    private AdministradorServices adminServices;

    @GetMapping("admin/dashboard")
    public String dashboard() {
        return "dashboard-admin";
    }

    @GetMapping("admin/mi_cuenta")
    public String miCuenta() {
        return "mi-cuenta";
    }

    @GetMapping("admin/mi_restaurant")
    public String miRestaurant() {
        return "mi-restaurant";
    }

    @GetMapping("admin/today_admin")
    public String todayAdmin() {
        return "today-admin";
    }

    @GetMapping("admin/tomorrow_admin")
    public String tomorrowAdmin() {
        return "tomorrow-admin";
    }

    @GetMapping("admin/next_day_admin")
    public String nextDayAdmin() {
        return "nextdays-admin";
    }

    @GetMapping("admin/login")
    public String login() {
        return "login-admin";
    }

    @GetMapping ("admin/administrar_usuarios")
    public String administrarUsuarios(Model model) {
        model.addAttribute("admins", adminServices.mostrarAdmins());
        model.addAttribute("adminRegisterForm", new Administrador());
        model.addAttribute("adminEditForm");
        return "administrar-usuarios";
    }

    @PostMapping("/admin/administrar_usuarios")
    public String agregarAdministrador(AdminRegisterDTO adminRegisterDTO) {
        try {
            adminServices.Registrar(adminRegisterDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/administrar_usuarios";
    }

//    @GetMapping("admin/login")
//    public String login(@RequestBody AdminLoginDTO adminLoginDTO, Model model) {
//        try {
//            model.addAttribute("adminLoginForm");
//            Administrador admin = adminServices.Logear(adminLoginDTO);
//            return "redirect:/dashboard";
//        } catch (Exception e) {
//            return "ERROR";
//        }
//    }

    @PutMapping("admin/editar/{id}")
    public String editar(@RequestBody Administrador adminInfo, @PathVariable Long id) {
        try {
            adminServices.editarAdministrador(adminInfo, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/administrar_usuarios";
    }

    @DeleteMapping ("admin/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        try {
            adminServices.eliminarAdministrador(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/administrar_usuarios";
    }
}
