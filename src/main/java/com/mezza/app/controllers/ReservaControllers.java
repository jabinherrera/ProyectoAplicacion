package com.mezza.app.controllers;

import com.mezza.app.models.Reserva;
import com.mezza.app.services.ReservaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
public class ReservaControllers {

    @Autowired
    private ReservaServices reservaServices;

    @PostMapping("reserva/guardar")
    public ResponseEntity<?> guardar(@RequestBody Reserva reserva) {
        try {
            reservaServices.guardar(reserva);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Reserva guardado exitosamente");
    }

    /*
    @GetMapping(value="/date/{date}")

    @CrossOrigin
    public List<Booking> getReservasPorFecha(@PathVariable String date) {
        LocalDate getDate = LocalDate.parse(date);
        return ReservaRepository.findAllBookingsByDate(getDate);
    }

    @GetMapping(value="/date/{date}/time/{time}/customer/{customerId}")
    public List<Booking> getFechaHoraId(@PathVariable String date, @PathVariable String time, @PathVariable Long customerId) {
        LocalDate getDate = LocalDate.parse(date);
        LocalTime getTime = LocalTime.parse(time);
        return ReservaRepository.getFechaHoraId(getDate, getTime, customerId);
    }

    @GetMapping(value="/customer/{customerId}")
    public List<Booking> getReservasPorId(@PathVariable Long customerId){
        return ReservaRepository.getReservasPorId(customerId);
    }
     */
}
