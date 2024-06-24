package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import com.entidades.buenSabor.domain.entities.Empresa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*@Repository: Marca la interfaz como un componente de repositorio de Spring,
lo que permite que Spring Data JPA genere automáticamente una implementación de esta interfaz
y la registre como un bean en el contexto de la aplicación.*/
@Repository
public interface ArticuloManufacturadoRepository extends BaseRepository<ArticuloManufacturado,Long> {

    /*findDetallesById(Long id): Define una consulta JPQL personalizada
    para obtener los detalles de un ArticuloManufacturado que no estén marcados como eliminados lógicamente.
    Utiliza la anotación @Query para especificar la consulta y @Param para vincular el parámetro.*/
    @Query("SELECT amd FROM ArticuloManufacturadoDetalle amd WHERE amd.articuloManufacturado.id = :id and amd.eliminado is false")
    List<ArticuloManufacturadoDetalle> findDetallesById(@Param("id") Long id);

    List<ArticuloManufacturado> findBySucursalId(Long sucursalId);
}
