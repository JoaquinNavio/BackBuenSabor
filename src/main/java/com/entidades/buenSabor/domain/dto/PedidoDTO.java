package com.entidades.buenSabor.domain.dto;

import com.entidades.buenSabor.domain.enums.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Long id;
    private Estado estado;
    private Long clienteId;
    private String clienteNombre;
    private List<DetallePedidoDTO> detallePedidos;
    private FacturaDTO factura;
    private LocalTime horaEstimadaFinalizacion;  // Nueva propiedad

}
