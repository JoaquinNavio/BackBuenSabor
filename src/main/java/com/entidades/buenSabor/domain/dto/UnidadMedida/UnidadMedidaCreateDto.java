package com.entidades.buenSabor.domain.dto.UnidadMedida;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UnidadMedidaCreateDto {
    private String denominacion;
    private Long sucursal_id;
}
