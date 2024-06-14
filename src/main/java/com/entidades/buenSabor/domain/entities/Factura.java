package com.entidades.buenSabor.domain.entities;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
@Audited
public class Factura extends Base {
    private LocalDate fechaFcturacion;
    private String mpPaymentId;
    private Double totalVenta;
    private Boolean pagado; // Nuevo campo booleano
}
