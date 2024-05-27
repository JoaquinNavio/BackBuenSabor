package com.entidades.buenSabor.domain.dto.PromocionDetalle;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromocionDetalleCreateDto {
    private Long detalleId;
    private Integer cantidad;
    private Long articuloId;
    private Long promocionId;
}
