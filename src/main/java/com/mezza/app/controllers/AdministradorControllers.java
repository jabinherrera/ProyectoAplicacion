package com.mezza.app.controllers;
import com.mezza.app.dtos.AdminEditDTO;
import com.mezza.app.dtos.AdminLoginDTO;
import com.mezza.app.dtos.AdminRegisterDTO;
import com.mezza.app.models.*;
import com.mezza.app.services.AdministradorServices;
import com.mezza.app.services.ReservaServices;
import com.mezza.app.services.RestaurantServices;
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
    @Autowired
    private RestaurantServices restaurantServices;
    //aaaaaaaaaaaaa

    @GetMapping("/admin/dashboard")
    public String dashboard(@PathVariable Long id, Model model) {
        model.addAttribute("cantidadReservasHoy", reservaServices.contarReservasHoy());
        model.addAttribute("cantidadReservasManana", reservaServices.contarReservasManana());
        return "dashboard-admin";
    }

    @GetMapping("/admin/mi_cuenta/{id}")
    public String miCuenta(@PathVariable("id") Long id, Model model) {
        model.addAttribute("miCuenta", adminServices.getAdminById(id));
        return "mi-cuenta";
    }

    @GetMapping("/admin/mi_restaurant")
    public String miRestaurant(Model model) {
        model.addAttribute("restaurants", restaurantServices.mostrarRestaurant());
        return "mi-restaurant";
    }

    @GetMapping("/admin/today_admin")
    public String todayAdmin(Model model) {
        model.addAttribute("reservasHoy", reservaServices.mostrarReservasHoy());
        return "today-admin";
    }

    @GetMapping("/admin/tomorrow_admin")
    public String tomorrowAdmin(Model model) {
        model.addAttribute("reservasManana", reservaServices.mostrarReservasManana());
        return "tomorrow-admin";
    }

    @GetMapping("/admin/next_day_admin")
    public String nextDayAdmin(Model model) {
        model.addAttribute("reservasHoy", reservaServices.mostrarReservas());
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
        model.addAttribute("adminEditForm", new AdminEditDTO());
        return "administrar-usuarios";
    }

    @GetMapping ("/admin/administrar_usuarios/editar/{id}")
    public String administrarEditar(@PathVariable Long id, Model model) {
        model.addAttribute("adminEditForm", adminServices.getAdminById(id));
        return "form-editar-admin";
    }



    @PostMapping("/admin/login")
    public String login(AdminLoginDTO adminLoginDTO) {
        try {
            Administrador admin = adminServices.Logear(adminLoginDTO);
            return "redirect:/admin/dashboard";
        } catch (Exception e) {
            return "ERROR";
        }
    }

    @PostMapping("/admin/administrar_usuarios/registrar")
    public String agregarAdministrador(AdminRegisterDTO adminRegisterDTO) {
        try {
            adminServices.Registrar(adminRegisterDTO);
        } catch (Exception e) {
            System.err.println(e);
            return "redirect:/admin/administrar_usuarios";
        }
        return "redirect:/admin/administrar_usuarios";
    }



    @PostMapping("/admin/administrar_usuarios/editar/{id}")
    public String editarAdministrador(AdminEditDTO adminEditDTO, @PathVariable("id") Long id) {
        try {
            adminServices.editarAdministrador(adminEditDTO, id);
        } catch (Exception e) {
            System.err.println(e);
            return "redirect:/admin/administrar_usuarios/editar/" + id;
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
