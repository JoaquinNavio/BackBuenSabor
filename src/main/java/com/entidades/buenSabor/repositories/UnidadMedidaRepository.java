package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.UnidadMedida;
import com.entidades.buenSabor.repositories.Base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadMedidaRepository extends BaseRepository<UnidadMedida,Long> {
}
