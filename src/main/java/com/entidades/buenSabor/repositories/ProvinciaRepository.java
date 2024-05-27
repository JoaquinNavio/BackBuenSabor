package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.Provincia;
import com.entidades.buenSabor.repositories.Base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinciaRepository extends BaseRepository<Provincia,Long> {
    Provincia findByNombre(String provinciaNombre);
    List<Provincia> findByPaisId(Long id);
}
