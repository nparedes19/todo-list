package com.users.Users.repository;

import com.users.Users.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository <Usuario, Long> {
}
