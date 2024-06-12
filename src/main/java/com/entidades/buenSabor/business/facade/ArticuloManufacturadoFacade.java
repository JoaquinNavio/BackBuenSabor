package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoEcommerseDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/*ArticuloManufacturadoFacade:
Esta interfaz extiende BaseFacade y agrega métodos específicos
para la gestión de entidades de tipo ArticuloManufacturado.
Además de las operaciones básicas, incluye métodos como getDetallesById, createWithDetails y updateWithDetails,
que se utilizan para gestionar los detalles específicos de los ArticuloManufacturado.*/
public interface ArticuloManufacturadoFacade extends BaseFacade<ArticuloManufacturadoDto, ArticuloManufacturadoCreateDto, ArticuloManufacturadoCreateDto, Long> {

    //Declaramos metodo getDetallesById en facade
    List<ArticuloManufacturadoDetalleDto> getDetallesById(Long id);

    //Declaramos metodo createWithDetails en facade
    ArticuloManufacturado createWithDetails(ArticuloManufacturadoCreateDto dto);

    ArticuloManufacturadoDto vincularImagenes(MultipartFile[] files, Long articuloId);

    //Declaramos metodo updateWithDetails en facade
    ArticuloManufacturado updateWithDetails(Long id,ArticuloManufacturadoCreateDto dto);

    List<ArticuloManufacturadoEcommerseDto> getManufacturadosEcommerse();
}
