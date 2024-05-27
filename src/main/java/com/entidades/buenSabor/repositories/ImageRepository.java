package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends BaseRepository<Image, Long> {

}
