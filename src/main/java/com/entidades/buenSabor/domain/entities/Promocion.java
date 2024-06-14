package com.entidades.buenSabor.domain.entities;

import com.entidades.buenSabor.domain.enums.TipoPromocion;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
@Audited
public class Promocion extends Base {
    private String denominacion;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private LocalTime horaDesde;
    private LocalTime horaHasta;
    private String descripcionDescuento;
    private Double precioPromocional;
    private TipoPromocion tipoPromocion;

    /*
    @OneToMany
    @JoinColumn(name = "promocion_id")
    @Builder.Default
    @NotAudited
    private Set<ImagenArticulo> imagenes = new HashSet<>();
    */

    @ManyToMany(mappedBy = "promociones")
    private Set<Sucursal> sucursales = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "promocion_id")
    @Builder.Default
    private Set<PromocionDetalle> detalles = new HashSet<>();

    @OneToOne
    @NotAudited
    private Image image;

}
