package com.entidades.buenSabor.domain.dto.ArticuloManufacturado;

import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleDto;
import com.entidades.buenSabor.domain.dto.BaseDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;
import com.entidades.buenSabor.domain.dto.Image.ImageDto;
import com.entidades.buenSabor.domain.dto.Insumo.ImagenArticuloDto;
import com.entidades.buenSabor.domain.dto.UnidadMedida.UnidadMedidaDto;
import com.entidades.buenSabor.domain.entities.Image;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import lombok.*;

import java.util.Set;
/*DTO:
Representa la forma de los datos cuando se envían o reciben del cliente.
Contiene toda la información que necesita ser mostrada o enviada.*/
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloManufacturadoDto extends BaseDto {
    private String denominacion;
    private String descripcion;
    private Integer tiempoEstimadoMinutos;
    private Double precioVenta;
    private String preparacion;
    private UnidadMedidaDto unidadMedida;
    private CategoriaDto categoria;
    private Set<ImagenArticuloDto> imagenes;
    private Long sucursal_id;
}
