package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.domain.dto.PedidoDTO;
import com.entidades.buenSabor.domain.entities.DetallePedido;
import com.entidades.buenSabor.domain.entities.Factura;
import com.entidades.buenSabor.domain.entities.Pedido;
import com.entidades.buenSabor.domain.enums.Estado;
import com.entidades.buenSabor.domain.enums.FormaPago;
import com.entidades.buenSabor.repositories.DetallePedidoRepository;
import com.entidades.buenSabor.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Transactional
    public Pedido savePedidoWithDetails(Pedido pedido) {
        Factura factura = new Factura();
        factura.setFechaFcturacion(LocalDate.now());
        factura.setTotalVenta(pedido.getTotal());

        if (pedido.getFormaPago() == FormaPago.MERCADO_PAGO) {
            factura.setMpPaymentId(pedido.getFactura().getMpPaymentId());
            factura.setPagado(true); // Set pagado a true para Mercado Pago
            pedido.setEstado(Estado.PENDIENTE_ENTREGA_MP);
        } else {
            factura.setPagado(false); // Set pagado a false para Efectivo
            pedido.setEstado(Estado.PENDIENTE_ENTREGA_PAGO_EFECTIVO);
        }

        pedido.setFactura(factura);
        Pedido savedPedido = pedidoRepository.save(pedido);

        for (DetallePedido detalle : pedido.getDetallePedidos()) {
            detalle.setPedido(savedPedido);
            detallePedidoRepository.save(detalle);
        }

        return savedPedido;
    }

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public List<PedidoDTO> getAllPedidoDTOs() {
        return pedidoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Pedido getPedidoById(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @Transactional
    public Pedido updatePedido(Long id, String estado) {
        Pedido existingPedido = getPedidoById(id);
        if (existingPedido != null) {
            Estado nuevoEstado = Estado.valueOf(estado);

            if (nuevoEstado == Estado.ENTREGADO && existingPedido.getEstado() == Estado.PENDIENTE_ENTREGA_PAGO_EFECTIVO) {
                Factura factura = existingPedido.getFactura();
                if (factura != null) {
                    factura.setPagado(true); // Actualiza el campo pagado a true
                }
            }

            existingPedido.setEstado(nuevoEstado);
            return pedidoRepository.save(existingPedido);
        }
        return null;
    }

    private PedidoDTO convertToDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setEstado(pedido.getEstado());
        dto.setClienteId(pedido.getUser().getId());
        dto.setClienteNombre(pedido.getUser().getNombre());
        return dto;
    }
}
