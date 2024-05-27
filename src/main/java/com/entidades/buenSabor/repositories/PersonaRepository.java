package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.Persona;
import com.entidades.buenSabor.repositories.Base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends BaseRepository<Persona,Long> {
}
