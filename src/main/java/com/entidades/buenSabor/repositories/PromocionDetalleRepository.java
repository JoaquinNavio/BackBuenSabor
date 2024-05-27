package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.PromocionDetalle;
import com.entidades.buenSabor.repositories.Base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocionDetalleRepository extends BaseRepository<PromocionDetalle, Long> {
}
