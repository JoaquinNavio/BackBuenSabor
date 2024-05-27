package com.entidades.buenSabor.domain.dto.Promocion;


import com.entidades.buenSabor.domain.dto.Base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromocionDetalleDto extends BaseDto {
    private Integer cantidad;
    //ArticuloDto??
    private Long articuloId;
    private PromocionDto promocion;
}
