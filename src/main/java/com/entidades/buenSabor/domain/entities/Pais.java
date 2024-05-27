package com.entidades.buenSabor.domain.entities;

import com.entidades.buenSabor.domain.entities.Base.Base;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
public class Pais extends Base {
    private String nombre;


}
