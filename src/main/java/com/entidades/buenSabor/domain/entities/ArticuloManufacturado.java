package com.entidades.buenSabor.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
//@Audited
public class ArticuloManufacturado extends Articulo {

    private String descripcion;
    private Integer tiempoEstimadoMinutos;
    private String preparacion;

    @OneToMany(mappedBy = "articuloManufacturado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ArticuloManufacturadoDetalle> detalles;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;
}
