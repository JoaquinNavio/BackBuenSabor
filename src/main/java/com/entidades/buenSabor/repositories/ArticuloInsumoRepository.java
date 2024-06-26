package com.entidades.buenSabor.repositories;


import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArticuloInsumoRepository extends BaseRepository<ArticuloInsumo, Long> {
    @Query("SELECT ai FROM ArticuloInsumo ai WHERE ai.esParaElaborar = false AND ai.eliminado = false")
    List<ArticuloInsumo> findByEsParaElaborarFalse();

    @Query("SELECT ai FROM ArticuloInsumo ai WHERE ai.sucursal.id = :sucursalId AND ai.eliminado = false")
    List<ArticuloInsumo> findBySucursalId(@Param("sucursalId") Long sucursalId);
}