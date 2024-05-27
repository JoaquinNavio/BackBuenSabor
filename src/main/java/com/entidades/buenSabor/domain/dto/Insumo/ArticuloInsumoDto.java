package com.entidades.buenSabor.domain.dto.Insumo;

import com.entidades.buenSabor.domain.dto.Base.BaseDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;
import com.entidades.buenSabor.domain.dto.UnidadMedida.UnidadMedidaDto;
import com.entidades.buenSabor.domain.entities.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloInsumoDto extends BaseDto {
    private String denominacion;
    private Double precioVenta;
    private UnidadMedidaDto unidadMedida;
    private Double precioCompra;
    private Integer stockActual;
    private Integer stockMaximo;
    private Boolean esParaElaborar;
    private CategoriaDto categoria;
    private Image image;
}
