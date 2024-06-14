package com.entidades.buenSabor.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@SuperBuilder
@Audited
public class ArticuloManufacturadoDetalle extends Base {
    /*Atributo de la clase*/
    private Integer cantidad;

    /*@ManyToOne: Relación muchos a uno con ArticuloInsumo y ArticuloManufacturado.
    * @JoinColumn(name = "articulo_insumo_id") y @JoinColumn(name = "articulo_manufacturado_id"):
    * Especifican las columnas de unión en la tabla correspondiente.*/
    @ManyToOne
    @JoinColumn(name = "articulo_insumo_id")
    private ArticuloInsumo articuloInsumo;

    @ManyToOne
    @JoinColumn(name = "articulo_manufacturado_id")
    private ArticuloManufacturado articuloManufacturado;
}
