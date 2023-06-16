package com.mezza.app.services;

import com.mezza.app.dtos.AdminLoginDTO;
import com.mezza.app.dtos.AdminRegisterDTO;
import com.mezza.app.models.Administrador;
import com.mezza.app.repositories.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AdministradorServices {

    @Autowired
    public AdministradorRepository administradorRepository;

    public void Registrar(AdminRegisterDTO adminRegisterDTO) throws Exception {

        verificarDatos(adminRegisterDTO);
        verificarEmail(adminRegisterDTO.getEmail());
        Administrador admin = new Administrador();
        admin.setEmail(adminRegisterDTO.getEmail());
        admin.setContrasena(adminRegisterDTO.getContrasena());

        administradorRepository.save(admin);
    }

    public Administrador Logear(AdminLoginDTO adminLoginDTO) throws Exception {

        Administrador admin = administradorRepository.findById(adminLoginDTO.getId()).orElseThrow(() -> new Exception("Administrador no registrado"));
        int intentos = 0;
        if(!admin.getContrasena().equals(adminLoginDTO.getContrasena())){
            intentos++;
            administradorRepository.save(admin);
            if (intentos > 3) {
                administradorRepository.save(admin);
                throw new Exception("Exceso de intentos");
            }
            throw new Exception("Contraseña incorrecta");
        }
        administradorRepository.save(admin);
        return admin;
    }


    public void editarAdministrador(Administrador adminInfo, Long id) {
        Administrador admin = administradorRepository.findById(id).get();
        admin.setEmail(adminInfo.getEmail());
        admin.setContrasena(adminInfo.getContrasena());
        admin.setNombre(adminInfo.getNombre());
        admin.setApellido(adminInfo.getApellido());

        administradorRepository.save(admin);
    }

    public void eliminarAdministrador(Long id) {

        administradorRepository.deleteById(id);
    }


    private void verificarEmail(String email) throws Exception {
        Optional<Administrador> admin = administradorRepository.findByEmail(email);
        if (admin.isPresent()) throw new Exception("Email ocupado");
    }

    private void verificarDatos(AdminRegisterDTO adminRegisterDTO) throws Exception {
        if (adminRegisterDTO.getEmail() == null) throw new Exception("Email no ingresado");
        if (adminRegisterDTO.getContrasena() == null) throw new Exception("Contraseña no ingresado");
        if (adminRegisterDTO.getNombre() == null) throw new Exception("Nombre no ingresada");
        if (adminRegisterDTO.getApellido() == null) throw new Exception("Apellido no ingresado");
    }

    public List<Administrador> mostrarAdmins() {
        return administradorRepository.findAll();
    }
}