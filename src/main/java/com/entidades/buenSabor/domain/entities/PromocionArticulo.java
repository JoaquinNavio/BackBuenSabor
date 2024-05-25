package com.entidades.buenSabor.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class PromocionArticulo {

    @EmbeddedId
    private PromocionArticuloId id = new PromocionArticuloId();

    @ManyToOne
    @MapsId("promocionId")
    @JoinColumn(name = "promocion_id")
    private Promocion promocion;

    @ManyToOne
    @MapsId("articuloId")
    @JoinColumn(name = "articulo_id")
    private Articulo articulo;

    private int cantidad;
}
