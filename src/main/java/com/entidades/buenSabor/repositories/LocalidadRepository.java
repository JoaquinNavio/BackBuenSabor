package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.Localidad;
import com.entidades.buenSabor.repositories.Base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalidadRepository extends BaseRepository<Localidad,Long> {
    List<Localidad> findByProvinciaId(Long id);
}
