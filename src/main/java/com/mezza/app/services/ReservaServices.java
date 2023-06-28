package com.mezza.app.services;

import com.mezza.app.models.Reserva;

import com.mezza.app.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    public String fechaHoy() {
        String fechaHoy = Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).toString();
        return fechaHoy;
    }

    public String fechaManana() {
        String fechaManana = Date.valueOf(LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).toString();
        return fechaManana;
    }

    public List<Reserva> mostrarReservas() {
        return reservaRepository.findAll();
    }

    public long contarReservasHoy() {
        return mostrarReservasHoy().stream().count();
    }

    public long contarReservasManana() {
        return mostrarReservasManana().stream().count();
    }

    public List<Reserva> mostrarReservasHoy() {
        List<Reserva> reservasHoy = new ArrayList<>();
        List<Reserva> reservas = reservaRepository.findAll();

        for (Reserva reserva  : reservas) {
            if (reserva.getFecha().toString().equals(fechaHoy())) {
                reservasHoy.add(reserva);
            }
        }

        return reservasHoy;
    }

    public List<Reserva> mostrarReservasManana() {
        List<Reserva> reservasManana = new ArrayList<>();
        List<Reserva> reservas = reservaRepository.findAll();

        for (Reserva reserva  : reservas) {
            if (reserva.getFecha().toString().equals(fechaManana())) {
                reservasManana.add(reserva);
            }
        }

        return reservasManana;
    }
}
