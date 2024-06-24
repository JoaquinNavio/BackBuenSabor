package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoEcommerseDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/*Se definen los servicios específicos para las entidades ArticuloManufacturado*/
public interface ArticuloManufacturadoService extends BaseService<ArticuloManufacturado, Long> {

    /*List<ArticuloManufacturadoDetalle> getDetallesById(Long id):
    Este método devuelve una lista de detalles de artículo manufacturado
    asociados a un artículo manufacturado específico identificado por su ID.*/
    List<ArticuloManufacturadoDetalle> getDetallesById(Long id);


    /*createWithDetails(ArticuloManufacturadoCreateDto dto):
    Crea un nuevo artículo manufacturado con detalles proporcionados en un DTO de creación*/
    ArticuloManufacturado createWithDetails(ArticuloManufacturadoCreateDto dto);

    ArticuloManufacturado vincularImagenes(MultipartFile[] files, Long articuloId);

    /*updateWithDetails(Long id, ArticuloManufacturadoCreateDto dto):
    Actualiza un artículo manufacturado existente, incluidos sus detalles,
    utilizando los datos proporcionados en un DTO de creación.*/
    ArticuloManufacturado updateWithDetails(Long id,ArticuloManufacturadoCreateDto dto);

    List<ArticuloManufacturadoEcommerseDto> getManufacturadosEcommerse();

    List<ArticuloManufacturado> findBySucursalId(Long sucursalId);

}
