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
//@Audited
public class Usuario  extends Base {
    private String auth0Id;
    private String userName;

}
