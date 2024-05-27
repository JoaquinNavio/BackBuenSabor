package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.Image;
import com.entidades.buenSabor.repositories.Base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends BaseRepository<Image, Long> {

}
