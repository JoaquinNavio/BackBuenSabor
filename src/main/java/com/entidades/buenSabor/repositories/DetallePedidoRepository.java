package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.DetallePedido;
import com.entidades.buenSabor.repositories.Base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends BaseRepository<DetallePedido,Long> {
}
