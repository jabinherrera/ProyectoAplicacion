package com.mezza.app.services;

import com.mezza.app.models.Reserva;

import com.mezza.app.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
