package com.users.Users.repository;

import com.users.Users.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Repository extends JpaRepository <Usuario, Long> {
    Optional<Usuario> findByTelefono(String telefono);
    Optional<Usuario> findByCorreo(String correo);
}
