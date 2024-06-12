package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.service.PedidoService;
import com.entidades.buenSabor.domain.dto.MercadoPago.PedidoMP;
import com.entidades.buenSabor.domain.dto.PedidoDTO;
import com.entidades.buenSabor.domain.entities.Pedido;
import com.entidades.buenSabor.domain.entities.PreferenceMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin("*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/por-forma-pago")
    public ResponseEntity<List<Map<String, Object>>> getPedidosPorFormaPago() {
        List<Map<String, Object>> data = pedidoService.getPedidosPorFormaPago();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/por-mes")
    public ResponseEntity<List<Map<String, Object>>> getPedidosPorMes() {
        List<Map<String, Object>> data = pedidoService.getPedidosPorMes();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/por-articulo")
    public ResponseEntity<List<Map<String, Object>>> getPedidosPorArticulo() {
        List<Map<String, Object>> data = pedidoService.getPedidosPorArticulo();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearPedido(@RequestBody Pedido pedido) {
        Pedido createdPedido = pedidoService.savePedidoWithDetails(pedido);
        return new ResponseEntity<>("Pedido creado con éxito", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> obtenerTodosLosPedidos() {
        List<PedidoDTO> pedidos = pedidoService.getAllPedidoDTOs();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> actualizarPedido(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String estado = payload.get("estado");
        Pedido pedidoExistente = pedidoService.updatePedido(id, estado);
        if (pedidoExistente != null) {
            return new ResponseEntity<>("Pedido actualizado con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Pedido no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/create_preference_mp")
    public PreferenceMP crearPreferenciaMercadoPago(@RequestBody PedidoMP pedido) {
        MercadoPagoController cMercadoPago = new MercadoPagoController();
        PreferenceMP preference = cMercadoPago.getPreferenciaIdMercadoPago(pedido);
        return preference;
    }
}
