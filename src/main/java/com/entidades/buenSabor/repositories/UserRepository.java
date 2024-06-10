package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNombreAndContraseña(String nombre, String contraseña);
    Optional<User> findByNombre(String nombre);
}
