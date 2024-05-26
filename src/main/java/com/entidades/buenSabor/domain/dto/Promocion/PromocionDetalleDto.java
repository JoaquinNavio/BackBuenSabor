package com.entidades.buenSabor.domain.dto.Promocion;


import com.entidades.buenSabor.domain.dto.BaseDto;
import com.entidades.buenSabor.domain.entities.Articulo;
import com.entidades.buenSabor.domain.entities.Promocion;
import com.entidades.buenSabor.domain.enums.TipoPromocion;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromocionDetalleDto extends BaseDto {
    private String detalle;
    private Long articuloId;
    private Long promocionId;
}
