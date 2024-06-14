package com.entidades.buenSabor.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.util.Set;

/*@Entity: Marca la clase como una entidad JPA.*/
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@Audited
public class ArticuloManufacturado extends Articulo {

    /*descripcion, tiempoEstimadoMinutos, preparacion: Atributos específicos de un artículo manufacturado*/
    private String descripcion;
    private Integer tiempoEstimadoMinutos;
    private String preparacion;

    /*@OneToMany(mappedBy = "articuloManufacturado", cascade = CascadeType.ALL, fetch = FetchType.LAZY):
    Relación uno a muchos con ArticuloManufacturadoDetalle.
    En este caso, un ArticuloManufacturado puede tener muchos ArticuloManufacturadoDetalle.
    mappedBy:Especifica el campo en la entidad ArticuloManufacturadoDetalle que se usa para mapear la relación inversa.
    Aquí, articuloManufacturado es el nombre del campo en ArticuloManufacturadoDetalle que apunta de vuelta a ArticuloManufacturado.
    cascade = CascadeType.ALL: Propaga todas las operaciones de la entidad padre (ArticuloManufacturado)
    a las entidades relacionadas (ArticuloManufacturadoDetalle).
    Esto incluye operaciones como persist, merge, remove, refresh y detach.
    fetch = FetchType.LAZY Indica que las entidades relacionadas no se cargan automáticamente cuando se carga la entidad padre.
    En su lugar, se cargan bajo demanda. Esto puede mejorar el rendimiento al evitar la carga innecesaria de datos.*/
    @OneToMany(mappedBy = "articuloManufacturado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ArticuloManufacturadoDetalle> detalles;

}
