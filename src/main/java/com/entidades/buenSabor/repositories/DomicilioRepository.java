package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.Domicilio;
import com.entidades.buenSabor.repositories.Base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicilioRepository extends BaseRepository<Domicilio,Long> {
}
