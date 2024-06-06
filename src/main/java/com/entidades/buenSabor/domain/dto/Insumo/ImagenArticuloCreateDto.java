package com.entidades.buenSabor.domain.dto.Insumo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImagenArticuloCreateDto {
    private String url;
    private Long idArticulo;
}
