package com.mezza.app.services;

import com.mezza.app.dtos.ReservaDTO;
import com.mezza.app.dtos.ReservaEditDTO;
import com.mezza.app.models.Reserva;

import com.mezza.app.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ReservaServices {

    @Autowired
    public ReservaRepository reservaRepository;

    public Reserva getReservaById(Long id){
        return reservaRepository.findById(id).get();
    }

    public void guardar(ReservaDTO reservaDTO) {
        try {
            Reserva reserva = new Reserva();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");

            reserva.setFecha(Date.valueOf(reservaDTO.getFecha()));
            reserva.setHora(LocalTime.parse(reservaDTO.getHora(), formato));
            reserva.setCant_personas(Integer.parseInt(reservaDTO.getCant_personas()));
            reserva.setNombre_cliente(reservaDTO.getNombre_cliente());
            reserva.setApellido_cliente(reservaDTO.getApellido_cliente());
            reserva.setEmail_cliente(reservaDTO.getEmail_cliente());
            reservaRepository.save(reserva);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void editar(ReservaEditDTO reservaEditDTO, Long id){
        Reserva reserva = reservaRepository.findById(id).get();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");

        reserva.setFecha(Date.valueOf(reservaEditDTO.getFecha()));
        reserva.setHora(LocalTime.parse(reservaEditDTO.getHora(), formato));
        reserva.setCant_personas(Integer.parseInt(reservaEditDTO.getCant_personas()));

        reservaRepository.save(reserva);

    }


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

    public List<Reserva> mostrarReservasProximas() {
        List<Reserva> reservasProximas = new ArrayList<>();
        List<Reserva> reservas = mostrarReservas();

        for (Reserva reserva  : reservas) {
            if (reserva.getFecha().after(Date.valueOf(fechaManana()))) {
                reservasProximas.add(reserva);
            }
        }


        return reservasProximas.stream()
                .sorted(Comparator.comparing(Reserva::getFecha))
                .toList();
    }
}
