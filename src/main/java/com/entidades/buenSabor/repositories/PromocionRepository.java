package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import com.entidades.buenSabor.domain.entities.Promocion;
import com.entidades.buenSabor.domain.entities.PromocionDetalle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromocionRepository extends BaseRepository<Promocion,Long>{
    @Query("SELECT amd FROM PromocionDetalle amd WHERE amd.promocion.id = :id and amd.eliminado is false")
    List<PromocionDetalle> findDetallesById(@Param("id") Long id);

    @Query("SELECT p FROM Promocion p WHERE p.sucursal.id = :sucursalId AND p.eliminado = false")
    List<Promocion> findBySucursalId(@Param("sucursalId") Long sucursalId);

}
