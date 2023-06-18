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
        Administrador admin = new Administrador();

        verificarDatos(adminRegisterDTO);
        verificarEmail(adminRegisterDTO.getEmail());
        admin.setApellido(adminRegisterDTO.getApellido());
        admin.setNombre(adminRegisterDTO.getNombre());
        admin.setEmail(adminRegisterDTO.getEmail());
        admin.setContrasena(adminRegisterDTO.getContrasena());



        administradorRepository.save(admin);
    }

//    public Administrador Logear(AdminLoginDTO adminLoginDTO) throws Exception {
//        Administrador admin = administradorRepository.findById(adminLoginDTO.getId()).orElseThrow(() -> new Exception("Administrador no registrado"));
//        int intentos = 0;
//        if(!admin.getContrasena().equals(adminLoginDTO.getContrasena())){
//            intentos++;
//            administradorRepository.save(admin);
//            if (intentos > 3) {
//                administradorRepository.save(admin);
//                throw new Exception("Exceso de intentos");
//            }
//            throw new Exception("Contraseña incorrecta");
//        }
//        administradorRepository.save(admin);
//        return admin;
//    }

    public Administrador Logear(AdminLoginDTO adminLoginDTO) throws Exception{
        Administrador admin = administradorRepository.findByEmail(adminLoginDTO.getEmail()).orElseThrow(()-> new Exception("Usuario no existe"));
        if(!admin.getContrasena().equals(adminLoginDTO.getContrasena())) throw new Exception("Contraseña Incorrecta");
        return admin;
    }


    public void editarAdministrador(AdminRegisterDTO adminRegisterDTO, Long id) {
        Administrador admin = administradorRepository.findById(id).get();
        admin.setEmail(adminRegisterDTO.getEmail());
        admin.setNombre(adminRegisterDTO.getNombre());
        admin.setApellido(adminRegisterDTO.getApellido());

        administradorRepository.save(admin);
    }

    public void eliminarAdministrador(Long id) {
        administradorRepository.delete(administradorRepository.findById(id).get());
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