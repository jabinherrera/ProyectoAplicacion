package com.mezza.app.controllers;
import com.mezza.app.dtos.AdminEditDTO;
import com.mezza.app.dtos.AdminLoginDTO;
import com.mezza.app.dtos.AdminRegisterDTO;
import com.mezza.app.dtos.ReservaEditDTO;
import com.mezza.app.models.*;
import com.mezza.app.services.AdministradorServices;
import com.mezza.app.services.ReservaServices;
import com.mezza.app.services.RestaurantServices;
import jakarta.servlet.http.HttpServletRequest;
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

    private String urlAnterior;
    public void setUrlAnterior(String nuevaUrl){ this.urlAnterior = nuevaUrl;}

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("cantidadReservasHoy", reservaServices.contarReservasHoy());
        model.addAttribute("cantidadReservasManana", reservaServices.contarReservasManana());
        return "dashboard-admin";
    }

    @GetMapping("/admin/mi_cuenta")
    public String miCuenta(Model model) {

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
        model.addAttribute("reservaEditForm", new ReservaEditDTO());
        setUrlAnterior("admin/today_admin");
        return "today-admin";
    }

    @GetMapping("/admin/tomorrow_admin")
    public String tomorrowAdmin(Model model) {
        model.addAttribute("reservasManana", reservaServices.mostrarReservasManana());
        model.addAttribute("reservaEditForm", new ReservaEditDTO());
        setUrlAnterior("admin/tomorrow_admin");
        return "tomorrow-admin";
    }

    @GetMapping("/admin/next_day_admin")
    public String nextDayAdmin(Model model) {
        model.addAttribute("reservasProximas", reservaServices.mostrarReservasProximas());
        model.addAttribute("reservaEditForm", new ReservaEditDTO());
        setUrlAnterior("admin/next_day_admin");
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

    @GetMapping("/admin/reserva/editar/{id}")
    public String editarThisReserva(@PathVariable Long id, Model model) {
        model.addAttribute("reservaEditForm", reservaServices.getReservaById(id));
        return "form-editar-reserva";
    }

    @PostMapping("/admin/reserva/editar/{id}")
    public String editarReserva(@PathVariable Long id, ReservaEditDTO reservaEditDTO){
        try {
            reservaServices.editar(reservaEditDTO, id);
            return "redirect:/" + this.urlAnterior;

        }catch (Exception e){
            return "ERROR";
        }

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

    @PostMapping("/admin/mi_cuenta/editar")
    public String editarMiCuenta(AdminEditDTO adminEditDTO, @PathVariable Long id){
        try{
            adminServices.editarAdministrador(adminEditDTO , id);
            return "redirect:/admin/mi_cuenta";
        }catch (Exception e){
            return "Error al editar cuenta";
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
