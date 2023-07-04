package com.mezza.app.services;

import com.mezza.app.dtos.AdminEditDTO;
import com.mezza.app.dtos.AdminEditMiCuentaDTO;
import com.mezza.app.dtos.AdminLoginDTO;
import com.mezza.app.dtos.AdminRegisterDTO;
import com.mezza.app.models.Administrador;
import com.mezza.app.repositories.AdministradorRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    public Administrador getAdminById(Long id){
        return administradorRepository.findById(id).get();
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


    public void editarAdministrador(AdminEditDTO adminEditDTO, Long id) {

        Administrador admin = administradorRepository.findById(id).get();
        try {
            verificarEmail(adminEditDTO.getEmail());
            admin.setEmail(adminEditDTO.getEmail());
            admin.setNombre(adminEditDTO.getNombre());
            admin.setApellido(adminEditDTO.getApellido());
            administradorRepository.save(admin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void editarMiCuenta(AdminEditMiCuentaDTO adminEditMiCuentaDTO, Long id) {

        Administrador admin = administradorRepository.findById(id).get();
        try {
            verificarEmail(adminEditMiCuentaDTO.getEmail());
            admin.setEmail(adminEditMiCuentaDTO.getEmail());
            admin.setNombre(adminEditMiCuentaDTO.getNombre());
            admin.setContrasena(adminEditMiCuentaDTO.getContrasena());
            administradorRepository.save(admin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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