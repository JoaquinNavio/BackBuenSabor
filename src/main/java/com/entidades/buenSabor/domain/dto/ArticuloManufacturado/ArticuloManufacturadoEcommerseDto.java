package com.entidades.buenSabor.domain.dto.ArticuloManufacturado;


import com.entidades.buenSabor.domain.dto.BaseDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;
import com.entidades.buenSabor.domain.dto.Insumo.ImagenArticuloDto;
import com.entidades.buenSabor.domain.dto.UnidadMedida.UnidadMedidaDto;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloManufacturadoEcommerseDto extends BaseDto {
    private String denominacion;
    private String descripcion;
    private Integer tiempoEstimadoMinutos;
    private Double precioVenta;
    private String preparacion;
    private UnidadMedidaDto unidadMedida;
    private Integer stock;
    private CategoriaDto categoria;
    private Set<ImagenArticuloDto> imagenes;
}
