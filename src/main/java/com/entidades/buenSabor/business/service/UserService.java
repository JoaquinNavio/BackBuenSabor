package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.entities.User;
import com.entidades.buenSabor.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> authenticate(String nombre, String contraseña) {
        return userRepository.findByNombreAndContraseña(nombre, contraseña);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByNombre(String nombre) {
        return userRepository.findByNombre(nombre);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setEliminado(true);  // Realizar eliminación lógica
            userRepository.save(user);
        }
    }
    public User updateUserRole(Long id, String newRole) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setRol(newRole);
            return userRepository.save(user);
        }
        return null;
    }
}
