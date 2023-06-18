package com.mezza.app.services;

import com.mezza.app.models.Administrador;
import com.mezza.app.models.Reserva;

import com.mezza.app.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaServices {

    @Autowired
    public ReservaRepository reservaRepository;

        public void guardar(Reserva reserva) {
            try {
                reservaRepository.save(reserva);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

//    public List<Reserva> mostrarReservasHoy() {
//        LocalDate fechaActual = LocalDate.now();
//        Predicate<Reserva> esFechaActual = reserva -> reserva.getFecha().equals(fechaActual);
//        List<Reserva> reservasSinFiltrar = reservaRepository.findAll();
//        List<Reserva> reservasHoy = reservasSinFiltrar.stream()
//                .filter(esFechaActual)
//                .collect(Collectors.toList());
//        return reservasHoy;
//    }

//    public List<Reserva> mostrarReservasHoy() {
//        LocalDate localDate = LocalDate.now();
//
//        int year = localDate.getYear();
//        int month = localDate.getMonthValue();
//        int day = localDate.getDayOfMonth();
//
//        Date hoy = new Date(year - 1900, month - 1, day);
//
//        return reservaRepository.findAll().stream().filter(reserva -> reserva.getFecha()==hoy).toList();
//    }

    // TODO: Poner fecha como string y buscarla con contain(fecha), fecha tiene que ser optional

    public List<Reserva> mostrarReservasHoy() {
        return reservaRepository.findAll();
    }
}
