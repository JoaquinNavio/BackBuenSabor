package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.UnidadMedidaService;
import com.entidades.buenSabor.domain.dto.UnidadMedida.UnidadMedidaCreateDto;
import com.entidades.buenSabor.domain.entities.Sucursal;
import com.entidades.buenSabor.domain.entities.UnidadMedida;
import com.entidades.buenSabor.repositories.SucursalRepository;
import com.entidades.buenSabor.repositories.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadMedidaServiceImp extends BaseServiceImp<UnidadMedida, Long> implements UnidadMedidaService {


    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    @Override
    public UnidadMedida create(UnidadMedidaCreateDto unidadMedidaCreateDto) {
        UnidadMedida unidadMedida = new UnidadMedida();
        unidadMedida.setDenominacion(unidadMedidaCreateDto.getDenominacion());

        Sucursal sucursal = sucursalRepository.findById(unidadMedidaCreateDto.getSucursal_id())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        unidadMedida.setSucursal(sucursal);

        return baseRepository.save(unidadMedida);
    }

    public List<UnidadMedida> findBySucursalId(Long sucursalId) {
        return unidadMedidaRepository.findBySucursalId(sucursalId);
    }
}
