package com.entidades.buenSabor.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@SuperBuilder
@Audited
public class Categoria extends Base{

    private String denominacion;
    private boolean esInsumo;
    private String url_imagen;

    /*@ManyToMany(mappedBy = "categorias")
    @Builder.Default
    private Set<Sucursal> sucursales = new HashSet<>();*/

    @OneToMany
    //SE AGREGA EL JOIN COLUMN PARA QUE JPA NO CREE LA TABLA INTERMEDIA EN UNA RELACION ONE TO MANY
    //DE ESTA MANERA PONE EL FOREIGN KEY 'categoria_id' EN LA TABLA DE LOS MANY
    @JoinColumn(name = "categoria_id")
    //SE AGREGA EL BUILDER.DEFAULT PARA QUE BUILDER NO SOBREESCRIBA LA INICIALIZACION DE LA LISTA
    @Builder.Default
    private Set<Articulo> articulos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "categoria_padre_id")
    private Categoria categoriaPadre;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;
}
