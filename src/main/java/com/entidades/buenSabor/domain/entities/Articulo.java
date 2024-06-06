package com.entidades.buenSabor.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.NotAudited;

import java.util.HashSet;
import java.util.Set;

/*@Entity: Marca la clase como una entidad JPA.*/
@Entity
/*@NoArgsConstructor: Genera un constructor sin argumentos.*/
@NoArgsConstructor
/*@AllArgsConstructor: Genera un constructor con todos los campos de la clase como argumentos.*/
@AllArgsConstructor
/*@Getter y @Setter: Generan automáticamente los métodos getter y setter para todos los campos.*/
@Getter
@Setter
/*@SuperBuilder: Genera un builder para la clase que puede ser usado para crear instancias de manera fluida.*/
@SuperBuilder
/*@Inheritance(strategy = InheritanceType.JOINED): Especifica la estrategia de herencia para JPA.
JOINED significa que cada clase en la jerarquía de herencia se mapea a su propia tabla en la base de datos.*/
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Articulo extends Base {

    /*denominacion y precioVenta: Atributos básicos del artículo.*/
    protected String denominacion;
    protected Double precioVenta;


    /*@OneToMany: Relación uno a muchos con ImagenArticulo.
    @JoinColumn(name = "articulo_id") especifica la columna de unión en la tabla ImagenArticulo.*/
    @OneToMany
    @JoinColumn(name = "articulo_id")
    /*@Builder.Default: Establece un valor predeterminado para el builder.*/
    @Builder.Default
    /*@NotAudited: Excluye este campo del proceso de auditoría.*/
    @NotAudited
    //@JsonIgnore
    protected Set<ImagenArticulo> imagenes = new HashSet<>();

    /*@ManyToOne: Relaciones muchos a uno con UnidadMedida y Categoria.*/
    @ManyToOne
    protected UnidadMedida unidadMedida;

    @ManyToOne
    //@JsonIgnore
    protected Categoria categoria;
}
