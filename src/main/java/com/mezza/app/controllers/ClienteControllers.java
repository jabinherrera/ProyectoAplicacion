package com.mezza.app.controllers;


import com.mezza.app.models.Cliente;
import com.mezza.app.services.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class ClienteControllers {

    @Autowired
    private ClienteServices clienteServices;

    @GetMapping("ver_cliente/{id}")
    public Cliente verCliente(@PathVariable Long id) {
        return clienteServices.getCliente(id);
    }
}
