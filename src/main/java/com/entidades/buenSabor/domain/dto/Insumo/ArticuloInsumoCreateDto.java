package com.entidades.buenSabor.domain.dto.Insumo;


import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    private MultipartFile[] files;
    private Long sucursal_id;
}
