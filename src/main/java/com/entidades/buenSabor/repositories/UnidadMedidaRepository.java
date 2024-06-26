package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.UnidadMedida;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UnidadMedidaRepository extends BaseRepository<UnidadMedida,Long> {

    @Query("SELECT um FROM UnidadMedida um WHERE um.sucursal.id = :sucursalId AND um.eliminado = false")
    List<UnidadMedida> findBySucursalId(@Param("sucursalId") Long sucursalId);
}
