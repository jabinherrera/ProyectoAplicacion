package com.mezza.app.services;

import com.mezza.app.models.Pago;
import com.mezza.app.repositories.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;


public class PagoServices {

    @Autowired
    public PagoRepository pagoRepository;

    public void guardar(@RequestBody Pago pago){
        pagoRepository.save(pago);
    }
}
