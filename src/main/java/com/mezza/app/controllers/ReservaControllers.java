package com.mezza.app.controllers;

import com.mezza.app.dtos.ReservaDTO;
import com.mezza.app.models.Reserva;
import com.mezza.app.repositories.ReservaRepository;
import com.mezza.app.services.ReservaServices;
import com.mezza.app.services.RestaurantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@Controller
@RequestMapping("/")
public class ReservaControllers {

    @Autowired
    private ReservaServices reservaServices;
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private RestaurantServices restaurantServices;

    @GetMapping()
    public String Home() {
        return "redirect:/reserva/guardar";
    }

    @GetMapping("reserva/guardar")
    public String reservar(Model model) {
        model.addAttribute("reservaForm", new Reserva());
        model.addAttribute("restaurants", restaurantServices.mostrarRestaurant());
        return "reserva";
    }

    @GetMapping("reserva/reserva_redirect")
    public String reservarRedirect(Model model) {
        model.addAttribute("restaurants", restaurantServices.mostrarRestaurant());
        return "reserva-redirect";
    }

    @PostMapping("/reserva/guardar")
    public String guardarReserva(ReservaDTO reservaDTO) {
        Reserva reserva = new Reserva();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");

        reserva.setFecha(Date.valueOf(reservaDTO.getFecha()));
        reserva.setHora(LocalTime.parse(reservaDTO.getHora(), formato));
        reserva.setCant_personas(Integer.parseInt(reservaDTO.getCant_personas()));
        reserva.setNombre_cliente(reservaDTO.getNombre_cliente());
        reserva.setApellido_cliente(reservaDTO.getApellido_cliente());
        reserva.setEmail_cliente(reservaDTO.getEmail_cliente());
        reservaServices.guardar(reserva);
        return "redirect:/reserva/reserva_redirect";
    }

}
