package com.entidades.buenSabor.domain.dto.ArticuloManufacturado;

import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleCreateDto;
import com.entidades.buenSabor.domain.entities.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/*CreateDTO: Se usa específicamente para la creación de nuevos registros.
Contiene sólo los datos necesarios para crear una nueva entidad,
excluyendo atributos generados o derivados como IDs.*/

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

    private Set<ArticuloManufacturadoDetalleCreateDto> detalles;
}
