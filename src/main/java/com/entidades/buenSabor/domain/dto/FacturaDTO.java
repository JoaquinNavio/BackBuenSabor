package com.entidades.buenSabor.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaDTO {
    private Long id;
    private LocalDate fechaFcturacion;
    private String mpPaymentId;
    private Double totalVenta;
    private Boolean pagado;
}
