package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido, Long> {

    List<Pedido> findByFechaPedidoBetween(LocalDate fechaInicio, LocalDate fechaFin);

    Pedido findTopByOrderByIdDesc();

    @Query("SELECT p.formaPago AS formaPago, COUNT(p) AS cantidad FROM Pedido p GROUP BY p.formaPago")
    List<Map<String, Object>> findPedidosPorFormaPago();

    @Query("SELECT MONTH(p.fechaPedido) AS mes, COUNT(p) AS cantidad FROM Pedido p GROUP BY MONTH(p.fechaPedido)")
    List<Map<String, Object>> findPedidosPorMes();

    @Query("SELECT d.articulo.denominacion AS articulo, SUM(d.cantidad) AS cantidad FROM Pedido p JOIN p.detallePedidos d GROUP BY d.articulo.denominacion")
    List<Map<String, Object>> findPedidosPorArticulo();
}
