package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.PromocionService;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import com.entidades.buenSabor.domain.entities.Promocion;
import com.entidades.buenSabor.domain.entities.PromocionDetalle;
import com.entidades.buenSabor.repositories.ArticuloManufacturadoRepository;
import com.entidades.buenSabor.repositories.PromocionDetalleRepository;
import com.entidades.buenSabor.repositories.PromocionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromocionServiceImp extends BaseServiceImp<Promocion, Long> implements PromocionService {
    @Override
    public List<PromocionDetalle> getDetallesById(Long id) {
        return ((PromocionRepository)baseRepository).findDetallesById(id);
    }
}
