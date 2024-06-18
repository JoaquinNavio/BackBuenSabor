package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.UnidadMedida;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UnidadMedidaRepository extends BaseRepository<UnidadMedida,Long> {

    List<UnidadMedida> findBySucursalId(Long sucursalId);
}
