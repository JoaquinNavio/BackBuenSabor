package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.service.EmailService;
import com.entidades.buenSabor.business.service.PedidoService;
import com.entidades.buenSabor.domain.dto.MercadoPago.PedidoMP;
import com.entidades.buenSabor.domain.dto.PedidoDTO;
import com.entidades.buenSabor.domain.entities.Pedido;
import com.entidades.buenSabor.domain.entities.PedidoPrintManager;
import com.entidades.buenSabor.domain.entities.PreferenceMP;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin("*")
public class PedidoController {

    @Autowired
    private EmailService emailService;

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
        try {
            Pedido createdPedido = pedidoService.savePedidoWithDetails(pedido);
            return new ResponseEntity<>("Pedido creado con éxito", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
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

    @GetMapping("/generarExcel")
    public ResponseEntity<String> generarExcel(@RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
                                               @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
                                               @RequestParam(value = "email", required = false) String email) {
        try {
            List<Pedido> pedidos = pedidoService.getPedidosByDateRange(fechaInicio, fechaFin);
            if (pedidos.isEmpty()) {
                return new ResponseEntity<>("No hay pedidos entre las fechas dadas.", HttpStatus.NO_CONTENT);
            }
            if (email != null && !email.isEmpty()) {
                PedidoPrintManager mPrintPedido = new PedidoPrintManager(pedidoService, emailService);
                mPrintPedido.enviarExcelPorEmail(pedidos, email, "Reporte de Pedidos", "Adjunto se encuentra el reporte de pedidos en el rango de fechas especificado.");
                return new ResponseEntity<>("El archivo Excel se ha enviado por correo electrónico.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se ha proporcionado un correo electrónico para enviar el archivo.", HttpStatus.BAD_REQUEST);
            }
        } catch (IOException | EmailException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
