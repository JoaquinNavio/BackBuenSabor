package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.Pais;
import com.entidades.buenSabor.repositories.Base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends BaseRepository<Pais,Long> {
}
