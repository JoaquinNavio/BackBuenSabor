package com.entidades.buenSabor.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
@JsonIgnoreProperties({"pedido"}) // Ignorar relaciones recursivas

public class DetallePedido extends Base {
    private Integer cantidad;
    private Double subTotal;

    @ManyToOne
    @JoinColumn(name = "articulo_id")
    private Articulo articulo;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}

