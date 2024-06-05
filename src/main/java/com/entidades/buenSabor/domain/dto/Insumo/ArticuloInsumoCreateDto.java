package com.entidades.buenSabor.domain.dto.Insumo;


import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloInsumoCreateDto {
    private String denominacion;
    private Double precioVenta;
    private Long idUnidadMedida;
    private Double precioCompra;
    private Integer stockActual;
    private Integer stockMaximo;
    private Boolean esParaElaborar;
    private Long idCategoria;
    private Set<ImagenArticuloCreateDto> images ;
}
