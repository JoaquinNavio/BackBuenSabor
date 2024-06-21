package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.ArticuloService;
import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.PromocionService;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionCreateDto;
import com.entidades.buenSabor.domain.entities.Promocion;
import com.entidades.buenSabor.domain.entities.PromocionDetalle;
import com.entidades.buenSabor.domain.entities.Sucursal;
import com.entidades.buenSabor.repositories.PromocionDetalleRepository;
import com.entidades.buenSabor.repositories.PromocionRepository;
import com.entidades.buenSabor.repositories.SucursalRepository;
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

    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    public List<PromocionDetalle> getDetallesById(Long id) {
        return ((PromocionRepository)baseRepository).findDetallesById(id);
    }

    @Override
    @Transactional
    public Promocion createWithDetails(PromocionCreateDto dto) {
        Promocion promocion = new Promocion();
        promocion.setDenominacion(dto.getDenominacion());
        promocion.setFechaDesde(dto.getFechaDesde());
        promocion.setFechaHasta(dto.getFechaHasta());
        promocion.setHoraDesde(dto.getHoraDesde());
        promocion.setHoraHasta(dto.getHoraHasta());
        promocion.setDescripcionDescuento(dto.getDescripcionDescuento());
        promocion.setPrecioPromocional(dto.getPrecioPromocional());
        promocion.setTipoPromocion(dto.getTipoPromocion());

        if (dto.getSucursal_id() != null) {
            Sucursal sucursal = sucursalRepository.findById(dto.getSucursal_id())
                    .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
            promocion.setSucursal(sucursal);
        }

        Promocion savedPromocion = baseRepository.save(promocion);

        List<PromocionDetalle> detalles = dto.getDetalles().stream().map(detalleDto -> {
            PromocionDetalle detalle = new PromocionDetalle();
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setArticulo(articuloService.getById(detalleDto.getArticuloId()));
            detalle.setPromocion(savedPromocion);
            return detalle;
        }).collect(Collectors.toList());

        detalleRepository.saveAll(detalles);

        return savedPromocion;
    }

    @Override
    @Transactional
    public Promocion updateWithDetails(Long id, PromocionCreateDto dto) {
        Promocion existingPromocion = baseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promocion not found with id: " + id));

        existingPromocion.setDenominacion(dto.getDenominacion());
        existingPromocion.setFechaDesde(dto.getFechaDesde());
        existingPromocion.setFechaHasta(dto.getFechaHasta());
        existingPromocion.setHoraDesde(dto.getHoraDesde());
        existingPromocion.setHoraHasta(dto.getHoraHasta());
        existingPromocion.setDescripcionDescuento(dto.getDescripcionDescuento());
        existingPromocion.setPrecioPromocional(dto.getPrecioPromocional());
        existingPromocion.setTipoPromocion(dto.getTipoPromocion());

        if (dto.getSucursal_id() != null) {
            Sucursal sucursal = sucursalRepository.findById(dto.getSucursal_id())
                    .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
            existingPromocion.setSucursal(sucursal);
        }

        Promocion updatedPromocion = baseRepository.save(existingPromocion);

        Map<Long, PromocionDetalle> existingDetallesMap = existingPromocion.getDetalles().stream()
                .collect(Collectors.toMap(PromocionDetalle::getId, detalle -> detalle));

        List<PromocionDetalle> nuevosDetalles = dto.getDetalles().stream().map(detalleDto -> {
            PromocionDetalle detalle;
            if (detalleDto.getDetalleId() != null && existingDetallesMap.containsKey(detalleDto.getDetalleId())) {
                detalle = existingDetallesMap.get(detalleDto.getDetalleId());
            } else {
                detalle = new PromocionDetalle();
                detalle.setPromocion(updatedPromocion);
            }
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setArticulo(articuloService.getById(detalleDto.getArticuloId()));
            return detalle;
        }).collect(Collectors.toList());

        detalleRepository.saveAll(nuevosDetalles);

        return updatedPromocion;
    }

    @Override
    public List<Promocion> findBySucursalId(Long sucursalId) {
        return ((PromocionRepository) baseRepository).findBySucursalId(sucursalId);
    }

}