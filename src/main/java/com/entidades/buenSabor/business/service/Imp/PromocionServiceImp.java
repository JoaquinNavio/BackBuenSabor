package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.ArticuloService;
import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.PromocionService;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionCreateDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import com.entidades.buenSabor.domain.entities.Promocion;
import com.entidades.buenSabor.domain.entities.PromocionDetalle;
import com.entidades.buenSabor.repositories.ArticuloManufacturadoRepository;
import com.entidades.buenSabor.repositories.PromocionDetalleRepository;
import com.entidades.buenSabor.repositories.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PromocionServiceImp extends BaseServiceImp<Promocion, Long> implements PromocionService {

    @Autowired
    private PromocionDetalleRepository detalleRepository;

    @Autowired
    private ArticuloService articuloService;

    @Override
    public List<PromocionDetalle> getDetallesById(Long id) {
        return ((PromocionRepository)baseRepository).findDetallesById(id);
    }

    @Override
    @Transactional
    public Promocion createWithDetails(PromocionCreateDto dto) {
        // Crear Promocion
        Promocion promocion = new Promocion();
        promocion.setDenominacion(dto.getDenominacion());
        promocion.setFechaDesde(dto.getFechaDesde());
        promocion.setFechaHasta(dto.getFechaHasta());
        promocion.setHoraDesde(dto.getHoraDesde());
        promocion.setHoraHasta(dto.getHoraHasta());
        promocion.setDescripcionDescuento(dto.getDescripcionDescuento());
        promocion.setPrecioPromocional(dto.getPrecioPromocional());
        promocion.setTipoPromocion(dto.getTipoPromocion());

        /*
        Image image = new Image();
        image.setId(dto.getIdImage());
        promocion.setImage(image);*/
        // Guardar Promocion
        Promocion savedPromocion = baseRepository.save(promocion);

        // Crear detalles y asociarlos
        List<PromocionDetalle> detalles = dto.getDetalles().stream().map(detalleDto -> {
            PromocionDetalle detalle = new PromocionDetalle();
            detalle.setCantidad(detalleDto.getCantidad());

            detalle.setArticulo(articuloService.getById(detalleDto.getArticuloId()));

            detalle.setPromocion(savedPromocion);
            return detalle;
        }).collect(Collectors.toList());

        // Guardar detalles
        detalleRepository.saveAll(detalles);

        return savedPromocion;
    }

    @Override
    @Transactional
    public Promocion updateWithDetails(Long id, PromocionCreateDto dto) {
        // Obtener la promocion existente por su ID
        Promocion existingPromocion = baseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promocion not found with id: " + id));

        // Actualizar los atributos de la promocion con los nuevos valores del DTO
        existingPromocion.setDenominacion(dto.getDenominacion());
        existingPromocion.setFechaDesde(dto.getFechaDesde());
        existingPromocion.setFechaHasta(dto.getFechaHasta());
        existingPromocion.setHoraDesde(dto.getHoraDesde());
        existingPromocion.setHoraHasta(dto.getHoraHasta());
        existingPromocion.setDescripcionDescuento(dto.getDescripcionDescuento());
        existingPromocion.setPrecioPromocional(dto.getPrecioPromocional());
        existingPromocion.setTipoPromocion(dto.getTipoPromocion());

    /*
        Long idImageToDelete = null;
        if (existingPromocion.getImage() != null && !existingPromocion.getImage().getId().equals(dto.getIdImage())) {
            idImageToDelete = existingPromocion.getImage().getId();
        }
        if (dto.getIdImage() != null) {
            Image image = new Image();
            image.setId(dto.getIdImage());
            existingPromocion.setImage(image);
        } else {
            existingPromocion.setImage(null);
        }*/

        // Guardar los cambios en la promocion
        Promocion updatedPromocion = baseRepository.save(existingPromocion);


        /// Obtener los detalles existentes de la promocion
        Map<Long, PromocionDetalle> existingDetallesMap = existingPromocion.getDetalles().stream()
                .collect(Collectors.toMap(PromocionDetalle::getId, detalle -> detalle));


        // Actualizar y crear los detalles de la promocion
        List<PromocionDetalle> nuevosDetalles = dto.getDetalles().stream().map(detalleDto -> {
            PromocionDetalle detalle;
            if (detalleDto.getDetalleId() != null && existingDetallesMap.containsKey(detalleDto.getDetalleId())) {
                // Actualizar el detalle existente
                detalle = existingDetallesMap.get(detalleDto.getDetalleId());
            } else {
                // Crear un nuevo detalle
                detalle = new PromocionDetalle();
                detalle.setPromocion(updatedPromocion);
            }
            detalle.setCantidad(detalleDto.getCantidad());

            detalle.setArticulo(articuloService.getById(detalleDto.getArticuloId()));

            return detalle;
        }).collect(Collectors.toList());

        // Guardar los detalles actualizados y nuevos
        detalleRepository.saveAll(nuevosDetalles);

        return updatedPromocion;
    }

}