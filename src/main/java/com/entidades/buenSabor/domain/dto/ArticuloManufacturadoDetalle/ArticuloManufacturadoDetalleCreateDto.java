package com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*CreateDTO: Se usa específicamente para la creación de nuevos registros.
Contiene sólo los datos necesarios para crear una nueva entidad,
excluyendo atributos generados o derivados como IDs.*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloManufacturadoDetalleCreateDto {
    private Long idDetalle;
    private Integer cantidad;
    private Long idArticuloInsumo;
    private Long idArticuloManufacturado;
}
