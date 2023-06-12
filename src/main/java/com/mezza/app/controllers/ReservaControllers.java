package com.mezza.app.controllers;

import com.mezza.app.models.Reserva;
import com.mezza.app.services.ReservaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ReservaControllers {

    @Autowired
    private ReservaServices reservaServices;

    @GetMapping("/reserva/guardar")
    public String reservar(Model model) {
        model.addAttribute("reservaForm", new Reserva());
        return "reserva";
    }

    @PostMapping("/reserva/guardar")
    public String guardarReserva(@ModelAttribute Reserva reservaForm) {
        reservaServices.guardar(reservaForm);
        return "reserva-redirect";
    }

}
