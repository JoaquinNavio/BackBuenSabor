package com.entidades.buenSabor.domain.dto.Categoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaCreateDto {
    private String denominacion;
    private boolean esInsumo;
    //private Long idCategoriaPadre;
}