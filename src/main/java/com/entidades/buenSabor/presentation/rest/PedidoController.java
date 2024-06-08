package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.service.PedidoService;
import com.entidades.buenSabor.domain.entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin("*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearPedido(@RequestBody Pedido pedido) {
        pedidoService.savePedidoWithDetails(pedido);
        return new ResponseEntity<>("Pedido creado con éxito", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerTodosLosPedidos() {
        List<Pedido> pedidos = pedidoService.getAllPedidos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        Pedido pedidoExistente = pedidoService.getPedidoById(id);
        if (pedidoExistente != null) {
            pedidoExistente.setEstado(pedido.getEstado());
            // Actualiza otros campos necesarios
            pedidoService.updatePedido(id, pedidoExistente);
            return new ResponseEntity<>("Pedido actualizado con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Pedido no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
