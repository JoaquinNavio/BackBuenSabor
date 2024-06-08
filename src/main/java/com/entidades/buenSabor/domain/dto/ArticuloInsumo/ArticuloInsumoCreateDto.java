package com.entidades.buenSabor.domain.dto.ArticuloInsumo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloInsumoCreateDto {
    private Double precioCompra;
    private Integer stockActual;
    private Integer stockMaximo;
    private Boolean esParaElaborar;
    private Long idImage;
    private Long idUnidadMedida;
    private Long idCategoria;
}
