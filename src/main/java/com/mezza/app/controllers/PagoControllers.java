package com.mezza.app.controllers;

import com.mezza.app.models.Pago;
import com.mezza.app.models.Reserva;
import com.mezza.app.services.PagoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class PagoControllers {

    @Autowired PagoServices pagoServices;

    @GetMapping("reserva/guardar")
    public String reservar(Model model) {
        model.addAttribute("reservaForm", new Reserva());
        return "reserva";
    }

    @PostMapping("reserva/pago/guardar")
    public ResponseEntity<?> guardar(@RequestBody Pago pago) {
        try {
            pagoServices.guardar(pago);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Pago guardado exitosamente");
    }
}
