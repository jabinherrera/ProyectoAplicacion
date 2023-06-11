package com.mezza.app.controllers;

import com.mezza.app.models.Reserva;
import com.mezza.app.services.ReservaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class ReservaControllers {

    @Autowired
    private ReservaServices reservaServices;

    @PostMapping("/reserva/guardar")
    public String guardar(@RequestBody Reserva reserva) {
        try {
            reservaServices.guardar(reserva);
        } catch (Exception e) {
            return "ERROR";
        }
        return "redirect:/reserva-redirect";
    }
}
