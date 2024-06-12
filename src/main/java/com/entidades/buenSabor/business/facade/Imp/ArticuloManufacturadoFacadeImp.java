package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.ArticuloManufacturadoFacade;
import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.mapper.ArticuloManufacturadoDetalleMapper;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.service.ArticuloManufacturadoService;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoEcommerseDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/*ArticuloManufacturadoFacadeImp:
Esta clase implementa la interfaz ArticuloManufacturadoFacade.
Utiliza la clase base BaseFacadeImp con parámetros específicos para ArticuloManufacturado.
Además de las operaciones CRUD heredadas de BaseFacadeImp,
esta clase también implementa métodos adicionales específicos para manejar los detalles de los artículos manufacturados.*/


/*@Service: Indica que la clase es un bean de servicio y debe ser escaneada automáticamente por Spring
para la inyección de dependencias.*/
@Service
public class ArticuloManufacturadoFacadeImp extends BaseFacadeImp<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoCreateDto, ArticuloManufacturadoCreateDto, Long> implements ArticuloManufacturadoFacade {
    /*Este atributo representa un mapper
    que se utilizará para convertir entidades de detalles de artículos manufacturados a DTOs y viceversa.*/
    private final ArticuloManufacturadoDetalleMapper articuloManufacturadoDetalleMapper;


    /*Este constructor recibe tres parámetros:
    * baseService: Un servicio que maneja operaciones CRUD en entidades de tipo ArticuloManufacturado.
    * baseMapper: Un mapper que se utilizará para convertir entre entidades de
    ArticuloManufacturado y sus correspondientes DTOs.
    * articuloManufacturadoDetalleMapper: Un mapper específico para convertir entre entidades y DTOs de detalles
    de artículos manufacturados.*/
    public ArticuloManufacturadoFacadeImp(BaseService<ArticuloManufacturado, Long> baseService, BaseMapper<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoCreateDto, ArticuloManufacturadoCreateDto> baseMapper, ArticuloManufacturadoDetalleMapper articuloManufacturadoDetalleMapper) {
        super(baseService, baseMapper);
        this.articuloManufacturadoDetalleMapper = articuloManufacturadoDetalleMapper;
    }

    /*getDetallesById(Long id):
    Este método sobrescrito de la interfaz ArticuloManufacturadoFacade
    obtiene los detalles de un artículo manufacturado por su ID.
    Utiliza el servicio base para obtener los detalles de la base de datos
    y luego mapea estos detalles a DTOs utilizando el articuloManufacturadoDetalleMapper*/
    @Override
    public List<ArticuloManufacturadoDetalleDto> getDetallesById(Long id) {
        System.out.println("EJECUTANDO getDetallesById(Long id) SOBREESCRITO - ArticuloManufacturadoFacadeImp");
        return ((ArticuloManufacturadoService) baseService).getDetallesById(id).stream().map(articuloManufacturadoDetalleMapper::toDTO).toList();
    }

    /*createWithDetails(ArticuloManufacturadoCreateDto dto):
    Este método sobrescrito de la interfaz ArticuloManufacturadoFacade
    crea un nuevo artículo manufacturado con sus detalles.
    Utiliza el servicio base para crear el artículo manufacturado y luego devuelve el resultado.*/
    @Override
    public ArticuloManufacturado createWithDetails(ArticuloManufacturadoCreateDto dto) {
        System.out.println("EJECUTANDO createWithDetails(ArticuloManufacturadoCreateDto dto) SOBREESCRITO - ArticuloManufacturadoFacadeImp");
        return ((ArticuloManufacturadoService) baseService).createWithDetails(dto);
    }

    @Override
    public ArticuloManufacturadoDto vincularImagenes(MultipartFile[] files, Long id) {
        ArticuloManufacturado articuloManufacturado = ((ArticuloManufacturadoService) baseService).vincularImagenes(files, id);
        return baseMapper.toDTO(articuloManufacturado);
    }
    /*updateWithDetails(Long id, ArticuloManufacturadoCreateDto dto):
    Este método sobrescrito de la interfaz ArticuloManufacturadoFacade
    actualiza un artículo manufacturado existente con nuevos detalles.
    Utiliza el servicio base para actualizar el artículo manufacturado y luego devuelve el resultado.*/
    @Override
    public ArticuloManufacturado updateWithDetails(Long id, ArticuloManufacturadoCreateDto dto) {
        System.out.println("EJECUTANDO updateWithDetails(Long id, ArticuloManufacturadoCreateDto dto) SOBREESCRITO - ArticuloManufacturadoFacadeImp");
        return ((ArticuloManufacturadoService) baseService).updateWithDetails(id, dto);
    }

    @Override
    public List<ArticuloManufacturadoEcommerseDto> getManufacturadosEcommerse() {
        System.out.println("entro al facade");
       return ((ArticuloManufacturadoService) baseService).getManufacturadosEcommerse() ;
    }


}