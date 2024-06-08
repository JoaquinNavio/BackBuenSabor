package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.domain.entities.Pedido;
import com.entidades.buenSabor.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public Pedido savePedidoWithDetails(Pedido pedido) {
        Pedido savedPedido = pedidoRepository.save(pedido);
        // Agrega la lógica para guardar detalles si es necesario
        return savedPedido;
    }

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido getPedidoById(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido updatePedido(Long id, Pedido pedido) {
        Pedido existingPedido = getPedidoById(id);
        if (existingPedido != null) {
            existingPedido.setEstado(pedido.getEstado());
            // Agrega otras actualizaciones necesarias aquí
            return pedidoRepository.save(existingPedido);
        }
        return null;
    }
}
