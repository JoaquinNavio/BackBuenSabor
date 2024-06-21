package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import com.entidades.buenSabor.domain.entities.Categoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends BaseRepository<Categoria,Long>{

    @Query("SELECT cat FROM Categoria cat WHERE cat.esInsumo = false and cat.eliminado is false")
    List<Categoria> getAllNoElaborar();

    //añado la logica para que no traiga los eliminados
    @Query("SELECT cat FROM Categoria cat WHERE cat.sucursal.id = :sucursalId AND cat.eliminado = false")
    List<Categoria> findBySucursalId(@Param("sucursalId") Long sucursalId);

}
