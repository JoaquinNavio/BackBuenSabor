package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import org.springframework.stereotype.Repository;

/*@Repository: Marca la interfaz como un componente de repositorio de Spring,
lo que permite que Spring Data JPA genere automáticamente una implementación de esta interfaz
y la registre como un bean en el contexto de la aplicación.*/
@Repository
public interface ArticuloManufacturadoDetalleRepository extends BaseRepository<ArticuloManufacturadoDetalle,Long> {
}
