package com.entidades.buenSabor.domain.dto.Categoria;

import com.entidades.buenSabor.domain.dto.BaseDto;
import com.entidades.buenSabor.domain.entities.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoriaDto extends BaseDto {
    private String denominacion;
    private boolean esInsumo;
    private CategoriaDto categoriaPadre;
    private Long sucursal_id;
}
