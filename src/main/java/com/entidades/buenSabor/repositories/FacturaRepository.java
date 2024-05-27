package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.Factura;
import com.entidades.buenSabor.repositories.Base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends BaseRepository<Factura,Long> {
}
