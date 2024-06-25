package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.service.PedidoService;
import com.entidades.buenSabor.business.service.UserService;
import com.entidades.buenSabor.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;


    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>("Usuario eliminado con éxito", HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        // Encriptar la contraseña con SHA-256
        String encryptedPassword = encryptPassword(user.getContraseña());
        user.setContraseña(encryptedPassword);

        Optional<User> optionalUser = userService.authenticate(user.getGmail(), user.getContraseña());

        if (optionalUser.isPresent()) {
            User authenticatedUser = optionalUser.get();
            Map<String, Object> response = new HashMap<>();
            response.put("id", authenticatedUser.getId());
            response.put("nombre", authenticatedUser.getNombre());
            response.put("gmail", authenticatedUser.getGmail());
            response.put("rol", authenticatedUser.getRol());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        // Verificar que el gmail sea único
        if (userService.findByGmail(user.getGmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Gmail ya registrado");
        }

        // Encriptar la contraseña con SHA-256
        String encryptedPassword = encryptPassword(user.getContraseña());
        user.setContraseña(encryptedPassword);
        user.setRol("user"); // Asignar rol por defecto

        User savedUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/getUserByUsername")
    public User getUserByUsername(@RequestParam String nombre) {
        return userService.findByNombre(nombre).orElse(null);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/users/{id}/role")
    public ResponseEntity<String> updateUserRole(@PathVariable Long id, @RequestBody String newRole) {
        String role = newRole.replace("\"", ""); // Remover cualquier comilla adicional
        User updatedUser = userService.updateUserRole(id, role);
        if (updatedUser != null) {
            return new ResponseEntity<>("Rol actualizado con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    private String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }



}
