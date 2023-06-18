package com.mezza.app.controllers;
import com.mezza.app.dtos.AdminLoginDTO;
import com.mezza.app.dtos.AdminRegisterDTO;
import com.mezza.app.models.*;
import com.mezza.app.services.AdministradorServices;
import com.mezza.app.services.ReservaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class AdministradorControllers {

    @Autowired
    private AdministradorServices adminServices;
    @Autowired
    private ReservaServices reservaServices;

    @GetMapping("/admin/dashboard")
    public String dashboard() {
        return "dashboard-admin";
    }

    @GetMapping("/admin/mi_cuenta")
    public String miCuenta() {
        return "mi-cuenta";
    }

    @GetMapping("/admin/mi_restaurant")
    public String miRestaurant() {
        return "mi-restaurant";
    }

    @GetMapping("/admin/today_admin")
    public String todayAdmin(Model model) {
        model.addAttribute("reservasHoy", reservaServices.mostrarReservasHoy());
        return "today-admin";
    }

    @GetMapping("/admin/tomorrow_admin")
    public String tomorrowAdmin() {
        return "tomorrow-admin";
    }

    @GetMapping("/admin/next_day_admin")
    public String nextDayAdmin() {
        return "nextdays-admin";
    }

    @GetMapping("/admin/login")
    public String login(Model model) {
        model.addAttribute("adminLoginForm", new AdminLoginDTO());
        return "login-admin";
    }

    @GetMapping ("/admin/administrar_usuarios")
    public String administrarUsuarios(Model model) {
        model.addAttribute("admins", adminServices.mostrarAdmins());
        model.addAttribute("adminRegisterForm", new Administrador());
        model.addAttribute("adminEditForm", new AdminRegisterDTO());
        return "administrar-usuarios";
    }

    @PostMapping("/admin/login")
    public String login(AdminLoginDTO adminLoginDTO) {
        try {
            Administrador admin = adminServices.Logear(adminLoginDTO);
            return "redirect:admin/dashboard";
        } catch (Exception e) {
            return "ERROR";
        }
    }

    @PostMapping("/admin/administrar_usuarios/registrar")
    public String agregarAdministrador(AdminRegisterDTO adminRegisterDTO) {
        try {
            adminServices.Registrar(adminRegisterDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/admin/administrar_usuarios";
    }

    @PostMapping("/admin/administrar_usuarios/editar/{id}")
    public String editarAdministrador(@RequestBody AdminRegisterDTO adminRegisterDTO, @PathVariable Long id) {
        try {
            adminServices.editarAdministrador(adminRegisterDTO, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/admin/administrar_usuarios";
    }

    @GetMapping ("/admin/administrar_usuarios/eliminar/{id}")
    public String eliminarAdministrador(@PathVariable Long id) {
        try {
            adminServices.eliminarAdministrador(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/admin/administrar_usuarios";
    }
}
