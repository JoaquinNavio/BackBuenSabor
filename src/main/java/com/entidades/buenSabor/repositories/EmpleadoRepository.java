package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends BaseRepository<Empleado,Long> {

    @Query("SELECT e FROM Empleado e WHERE e.sucursal.id = :sucursalId AND e.eliminado = false")
    List<Empleado> findBySucursalId(Long sucursalId);

    @Query("SELECT e FROM Empleado e WHERE e.email = :email AND e.eliminado = false")
    Optional<Empleado> findByEmail(@Param("email") String email); // Agregar este método

}
