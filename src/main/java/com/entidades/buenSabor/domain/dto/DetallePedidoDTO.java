package com.entidades.buenSabor.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoDTO {
    private Long id;
    private Integer cantidad;
    private Double subTotal;
    private String articuloDenominacion;
}
