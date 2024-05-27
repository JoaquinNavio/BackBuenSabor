package com.entidades.buenSabor.domain.dto.ArticuloManufacturado;

import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloManufacturadoCreateDto {
    private String denominacion;
    private String descripcion;
    private Integer tiempoEstimadoMinutos;
    private Double precioVenta;
    private String preparacion;
    private Long idUnidadMedida;
    private Long idCategoria;
    private Long idImage;
    private Set<ArticuloManufacturadoDetalleCreateDto> detalles;
}
