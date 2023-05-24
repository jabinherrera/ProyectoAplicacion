package com.mezza.app.services;

import com.mezza.app.models.Reserva;

import com.mezza.app.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ReservaServices {

    @Autowired
    public ReservaRepository reservaRepository;

    public void guardar(@RequestBody Reserva reserva){
        reservaRepository.save(reserva);
    }
}
