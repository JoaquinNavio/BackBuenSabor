package com.entidades.buenSabor.domain.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ArticuloManufacturado.class, name = "articuloManufacturado"),
        @JsonSubTypes.Type(value = ArticuloInsumo.class, name = "articuloInsumo")
})
@JsonIgnoreProperties({"imagenes", "unidadMedida", "categoria"}) // Ignorar relaciones recursivas
@Audited
public abstract class Articulo extends Base {

    protected String denominacion;
    protected Double precioVenta;

    @OneToMany
    @JoinColumn(name = "articulo_id")
    @Builder.Default
    @NotAudited
    @JsonManagedReference // Agregar esta anotación
    protected Set<ImagenArticulo> imagenes = new HashSet<>();

    @ManyToOne
    protected UnidadMedida unidadMedida;

    @ManyToOne
    //@JsonIgnore
    protected Categoria categoria;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    @Override
    public String toString() {
        return "Articulo{" +
                "id=" + getId() +
                ", denominacion='" + denominacion + '\'' +
                ", precioVenta=" + precioVenta +
                // Omitiendo relaciones para evitar recursión infinita
                '}';
    }
}
