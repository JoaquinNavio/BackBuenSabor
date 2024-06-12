package com.entidades.buenSabor.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@CrossOrigin(origins = "*")
@Getter
@Setter
public class User extends Base {
    @Column(unique = true)
    private String gmail;
    private String nombre;
    private String contraseña;
    public String rol;
}
