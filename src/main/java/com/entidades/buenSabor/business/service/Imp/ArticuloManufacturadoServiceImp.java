package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.*;
import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import com.entidades.buenSabor.domain.entities.Image;
import com.entidades.buenSabor.repositories.ArticuloManufacturadoDetalleRepository;
import com.entidades.buenSabor.repositories.ArticuloManufacturadoRepository;
import com.entidades.buenSabor.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticuloManufacturadoServiceImp extends BaseServiceImp<ArticuloManufacturado, Long> implements ArticuloManufacturadoService {

    @Autowired
    private ArticuloManufacturadoRepository baseRepository;

    @Autowired
    private ArticuloManufacturadoDetalleRepository detalleRepository;

    @Autowired
    private UnidadMedidaService unidadMedidaService;

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private ImageService imageService;

    @Autowired
    private ArticuloInsumoService articuloInsumoService;

    @Override
    public List<ArticuloManufacturadoDetalle> getDetallesById(Long id) {
        return baseRepository.findDetallesById(id);
    }

    @Override
    @Transactional
    public ArticuloManufacturado createWithDetails(ArticuloManufacturadoCreateDto dto) {
        // Crear ArticuloManufacturado
        ArticuloManufacturado articuloManufacturado = new ArticuloManufacturado();
        articuloManufacturado.setDenominacion(dto.getDenominacion());
        articuloManufacturado.setDescripcion(dto.getDescripcion());
        articuloManufacturado.setTiempoEstimadoMinutos(dto.getTiempoEstimadoMinutos());
        articuloManufacturado.setPrecioVenta(dto.getPrecioVenta());
        articuloManufacturado.setPreparacion(dto.getPreparacion());
        articuloManufacturado.setUnidadMedida(unidadMedidaService.getById(dto.getIdUnidadMedida()));
        articuloManufacturado.setCategoria(categoriaService.getById(dto.getIdCategoria()));
        Image image = new Image();
        image.setId(dto.getIdImage());
        articuloManufacturado.setImage(image);
        // Guardar ArticuloManufacturado
        ArticuloManufacturado savedArticuloManufacturado = baseRepository.save(articuloManufacturado);

        // Crear detalles y asociarlos
        List<ArticuloManufacturadoDetalle> detalles = dto.getDetalles().stream().map(detalleDto -> {
            ArticuloManufacturadoDetalle detalle = new ArticuloManufacturadoDetalle();
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setArticuloInsumo(articuloInsumoService.getById(detalleDto.getIdArticuloInsumo()));
            detalle.setArticuloManufacturado(savedArticuloManufacturado);
            return detalle;
        }).collect(Collectors.toList());

        // Guardar detalles
        detalleRepository.saveAll(detalles);

        return savedArticuloManufacturado;
    }

    @Override
    @Transactional
    public ArticuloManufacturado updateWithDetails(Long id, ArticuloManufacturadoCreateDto dto) {
        // Obtener el artículo manufacturado existente por su ID
        ArticuloManufacturado existingArticuloManufacturado = baseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ArticuloManufacturado not found with id: " + id));

        // Actualizar los atributos del artículo manufacturado con los nuevos valores del DTO
        existingArticuloManufacturado.setDenominacion(dto.getDenominacion());
        existingArticuloManufacturado.setDescripcion(dto.getDescripcion());
        existingArticuloManufacturado.setTiempoEstimadoMinutos(dto.getTiempoEstimadoMinutos());
        existingArticuloManufacturado.setPrecioVenta(dto.getPrecioVenta());
        existingArticuloManufacturado.setPreparacion(dto.getPreparacion());
        existingArticuloManufacturado.setUnidadMedida(unidadMedidaService.getById(dto.getIdUnidadMedida()));
        existingArticuloManufacturado.setCategoria(categoriaService.getById(dto.getIdCategoria()));

        Long idImageToDelete = null;
        if (existingArticuloManufacturado.getImage() !=null && !existingArticuloManufacturado.getImage().getId().equals(dto.getIdImage())) {
            idImageToDelete= existingArticuloManufacturado.getImage().getId();
        }
        if(dto.getIdImage() != null){
            Image image = new Image();
            image.setId(dto.getIdImage());
            existingArticuloManufacturado.setImage(image);
        } else {
            existingArticuloManufacturado.setImage(null);
        }


        // Guardar los cambios en el artículo manufacturado
        ArticuloManufacturado updatedArticuloManufacturado = baseRepository.save(existingArticuloManufacturado);

        if (idImageToDelete!=null){
            imageService.deleteById(idImageToDelete);
        }


        // Eliminar los detalles existentes del artículo manufacturado

        existingArticuloManufacturado.getDetalles().forEach(detalle -> detalleRepository.delete(detalle));


        // Crear y asociar los nuevos detalles
        List<ArticuloManufacturadoDetalle> detalles = dto.getDetalles().stream().map(detalleDto -> {
            ArticuloManufacturadoDetalle detalle = new ArticuloManufacturadoDetalle();
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setArticuloInsumo(articuloInsumoService.getById(detalleDto.getIdArticuloInsumo()));
            detalle.setArticuloManufacturado(updatedArticuloManufacturado);
            return detalle;
        }).collect(Collectors.toList());

        // Guardar los nuevos detalles
        detalleRepository.saveAll(detalles);

        return updatedArticuloManufacturado;
    }
}