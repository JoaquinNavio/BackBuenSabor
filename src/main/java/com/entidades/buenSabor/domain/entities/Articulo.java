package com.entidades.buenSabor.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.NotAudited;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
//@Audited
public abstract class Articulo extends Base {

    protected String denominacion;
    protected Double precioVenta;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinTable(name = "promocion_articulo",
            joinColumns = @JoinColumn(name = "promocion_id"),
            inverseJoinColumns = @JoinColumn(name = "articulo_id"))
    //SE AGREGA EL BUILDER.DEFAULT PARA QUE BUILDER NO SOBREESCRIBA LA INICIALIZACION DE LA LISTA
    @Builder.Default
    protected Set<Promocion> promociones = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "articulo_id")
    @Builder.Default
    @NotAudited
    protected Set<ImagenArticulo> imagenes = new HashSet<>();

    @ManyToOne
    protected UnidadMedida unidadMedida;

    @ManyToOne
    protected Categoria categoria;
}
