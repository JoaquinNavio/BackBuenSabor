package com.entidades.buenSabor.domain.dto.Promocion;


import com.entidades.buenSabor.domain.dto.PromocionDetalle.PromocionDetalleCreateDto;
import com.entidades.buenSabor.domain.enums.TipoPromocion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PromocionCreateDto {
    private String denominacion;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private LocalTime horaDesde;
    private LocalTime horaHasta;
    private String descripcionDescuento;
    private Double precioPromocional;
    private TipoPromocion tipoPromocion;
    private Set<PromocionDetalleCreateDto> detalles;
}
