package com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloManufacturadoDetalleCreateDto {
    private Long idDetalle;
    private Integer cantidad;
    private Long idArticuloInsumo;
    private Long idArticuloManufacturado;
}
