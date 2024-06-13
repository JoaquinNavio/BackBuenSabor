package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.domain.dto.DetallePedidoDTO;
import com.entidades.buenSabor.domain.dto.FacturaDTO;
import com.entidades.buenSabor.domain.dto.PedidoDTO;
import com.entidades.buenSabor.domain.entities.*;
import com.entidades.buenSabor.domain.enums.Estado;
import com.entidades.buenSabor.domain.enums.FormaPago;
import com.entidades.buenSabor.repositories.ArticuloInsumoRepository;
import com.entidades.buenSabor.repositories.ArticuloManufacturadoRepository;
import com.entidades.buenSabor.repositories.DetallePedidoRepository;
import com.entidades.buenSabor.repositories.PedidoRepository;

import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.UnitValue;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private ArticuloInsumoRepository articuloInsumoRepository;

    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private EmailService emailService;
    // Charts
    public List<Map<String, Object>> getPedidosPorFormaPago() {
        List<Pedido> pedidos = getAllPedidos();
        return pedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getFormaPago, Collectors.counting()))
                .entrySet()
                .stream()
                .map(entry -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("formaPago", entry.getKey().toString());
                    map.put("cantidad", entry.getValue());
                    return map;
                })
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getPedidosPorMes() {
        List<Pedido> pedidos = getAllPedidos();
        return pedidos.stream()
                .collect(Collectors.groupingBy(p -> p.getFechaPedido().getMonth(), Collectors.counting()))
                .entrySet()
                .stream()
                .map(entry -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("mes", entry.getKey().toString());
                    map.put("cantidad", entry.getValue());
                    return map;
                })
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> getPedidosPorArticulo() {
        List<Pedido> pedidos = getAllPedidos();
        Map<String, Integer> conteoArticulos = new HashMap<>();
        pedidos.forEach(pedido -> {
            pedido.getDetallePedidos().forEach(detalle -> {
                String articulo = detalle.getArticulo().getDenominacion();
                conteoArticulos.put(articulo, conteoArticulos.getOrDefault(articulo, 0) + detalle.getCantidad());
            });
        });
        return conteoArticulos.entrySet()
                .stream()
                .map(entry -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("articulo", entry.getKey());
                    map.put("cantidad", entry.getValue());
                    return map;
                })
                .collect(Collectors.toList());
    }

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
        validarYActualizarStock(pedido);

        Pedido savedPedido = pedidoRepository.save(pedido);
        for (DetallePedido detalle : pedido.getDetallePedidos()) {
            detalle.setPedido(savedPedido);
            detallePedidoRepository.save(detalle);
        }
        return savedPedido;
    }

    private void validarYActualizarStock(Pedido pedido) {
        Map<Long, Integer> stockRequerido = new HashMap<>();
        for (DetallePedido detalle : pedido.getDetallePedidos()) {
            Long articuloId = detalle.getArticulo().getId();
            int cantidad = detalle.getCantidad();
            Articulo articulo = findArticuloById(articuloId);
            if (articulo instanceof ArticuloInsumo) {
                stockRequerido.merge(articuloId, cantidad, Integer::sum);
            } else if (articulo instanceof ArticuloManufacturado) {
                ArticuloManufacturado manufacturado = (ArticuloManufacturado) articulo;
                for (ArticuloManufacturadoDetalle amd : manufacturado.getDetalles()) {
                    Long insumoId = amd.getArticuloInsumo().getId();
                    int cantidadRequerida = amd.getCantidad() * cantidad;
                    stockRequerido.merge(insumoId, cantidadRequerida, Integer::sum);
                }
            }
        }

        for (Map.Entry<Long, Integer> entry : stockRequerido.entrySet()) {
            ArticuloInsumo insumo = articuloInsumoRepository.findById(entry.getKey())
                    .orElseThrow(() -> new RuntimeException("ArticuloInsumo no encontrado: " + entry.getKey()));
            if (insumo.getStockActual() < entry.getValue()) {
                throw new RuntimeException("No hay stock suficiente para el ArticuloInsumo: " + insumo.getDenominacion());
            }
            insumo.setStockActual(insumo.getStockActual() - entry.getValue());
            articuloInsumoRepository.save(insumo);
        }
    }

    private Articulo findArticuloById(Long id) {
        Optional<ArticuloInsumo> articuloInsumoOpt = articuloInsumoRepository.findById(id);
        if (articuloInsumoOpt.isPresent()) {
            return articuloInsumoOpt.get();
        }

        Optional<ArticuloManufacturado> articuloManufacturadoOpt = articuloManufacturadoRepository.findById(id);
        if (articuloManufacturadoOpt.isPresent()) {
            return articuloManufacturadoOpt.get();
        }

        throw new RuntimeException("Articulo no encontrado: " + id);
    }

    public List<Pedido> getPedidosByDateRange(LocalDate fechaInicio, LocalDate fechaFin) {
        return pedidoRepository.findByFechaPedidoBetween(fechaInicio, fechaFin);
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
                    factura.setMpPaymentId(UUID.randomUUID().toString());//numero aleatorio y unico
                    existingPedido.setFactura(factura);
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

        // Convertir detallePedidos a DetallePedidoDTO
        List<DetallePedidoDTO> detallesDTO = pedido.getDetallePedidos().stream()
                .map(detalle -> new DetallePedidoDTO(detalle.getId(), detalle.getCantidad(), detalle.getSubTotal(), detalle.getArticulo().getDenominacion()))
                .collect(Collectors.toList());
        dto.setDetallePedidos(detallesDTO);

        // Convertir factura a FacturaDTO
        Factura factura = pedido.getFactura();
        FacturaDTO facturaDTO = new FacturaDTO(factura.getId(), factura.getFechaFcturacion(), factura.getMpPaymentId(), factura.getTotalVenta(), factura.getPagado());
        dto.setFactura(facturaDTO);

        return dto;
    }






    public void generarYEnviarPDF(Long pedidoId, String email) throws IOException {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(pedidoId);
        if (optionalPedido.isPresent()) {
            Pedido pedido = optionalPedido.get();

            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                generatePedidoPDF(pedidoId, outputStream);

                String subject = "Factura de Pedido " + pedidoId;
                String text = "Adjunto encontrarás la factura de tu pedido.";
                emailService.sendEmailWithAttachment(email, subject, text, outputStream, "FacturaPedido.pdf");
            } catch (EmailException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IOException("Pedido no encontrado");
        }
    }

    // Método existente para generar el PDF del pedido
    public void generatePedidoPDF(Long pedidoId, ByteArrayOutputStream outputStream) throws IOException {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(pedidoId);
        if (optionalPedido.isPresent()) {
            Pedido pedido = optionalPedido.get();

            try (PdfWriter writer = new PdfWriter(outputStream);
                 PdfDocument pdf = new PdfDocument(writer)) {
                Document document = new Document(pdf, PageSize.A4);

                addHeader(document);

                // Añadir título de Datos del Pedido
                Paragraph pedidoTitle = new Paragraph("Datos del Pedido")
                        .setFontSize(18)
                        .setBold();
                document.add(pedidoTitle);
                addEmptyLine(document, 1);

                addPedidoInfo(document, pedido);

                // Añadir título de Datos de la Factura
                Paragraph facturaTitle = new Paragraph("Datos de la Factura")
                        .setFontSize(18)
                        .setBold();
                document.add(facturaTitle);
                addEmptyLine(document, 1);

                if (pedido.getFactura() != null) {
                    addFacturaInfo(document, pedido.getFactura());
                }

                addBarcode(document, pedidoId.toString());

                document.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new IOException("Pedido no encontrado");
        }
    }

    // Métodos auxiliares
    private void addPedidoInfo(Document document, Pedido pedido) {
        float[] columnWidths = {1, 3};
        Table table = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();

        try {
            addInfoRowToTable(table, "Cliente:", pedido.getUser().getNombre());
            addInfoRowToTable(table, "Fecha de Pedido:", pedido.getFechaPedido().toString());
            addInfoRowToTable(table, "Estado:", pedido.getEstado().toString());
            addInfoRowToTable(table, "Forma de Pago:", pedido.getFormaPago().toString());
            addInfoRowToTable(table, "Total:", String.valueOf(pedido.getTotal()));

            table.addCell(new Cell(1, 2).add(new Paragraph("Detalle del Pedido")).setBorder(Border.NO_BORDER).setBold());
            for (DetallePedido detalle : pedido.getDetallePedidos()) {
                addInfoRowToTable(table, detalle.getArticulo().getDenominacion(), "Cantidad: " + detalle.getCantidad() + ", Subtotal: " + detalle.getSubTotal());
            }

            document.add(table);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addFacturaInfo(Document document, Factura factura) {
        float[] columnWidths = {1, 3};
        Table table = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();

        try {
            addInfoRowToTable(table, "Fecha de Facturación:", factura.getFechaFcturacion().toString());
            addInfoRowToTable(table, "Total Venta:", String.valueOf(factura.getTotalVenta()));
            addInfoRowToTable(table, "Pagado:", factura.getPagado() ? "Sí" : "No");
            addInfoRowToTable(table, "ID de Pago MP:", factura.getMpPaymentId() != null ? factura.getMpPaymentId() : "N/A");

            document.add(table);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addInfoRowToTable(Table table, String attribute, String value) {
        Cell attributeCell = new Cell().add(new Paragraph(attribute)).setBorder(Border.NO_BORDER);
        Cell valueCell = new Cell().add(new Paragraph(value)).setBorder(Border.NO_BORDER);

        table.addCell(attributeCell);
        table.addCell(valueCell);
    }

    private void addEmptyLine(Document document, int lines) {
        for (int i = 0; i < lines; i++) {
            document.add(new Paragraph("\n"));
        }
    }

    private void setLineaReporte(Document document) {
        document.add(new LineSeparator(new SolidLine()));
        addEmptyLine(document, 2);
    }

    private void addBarcode(Document document, String text) {
        Barcode128 barcode = new Barcode128(document.getPdfDocument());
        barcode.setCode(text);

        Image barcodeImage = new Image(barcode.createFormXObject(document.getPdfDocument()));
        barcodeImage.scaleAbsolute(200, 100);

        addEmptyLine(document, 2);

        barcodeImage.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(barcodeImage);
    }

    private void addHeader(Document document) {
        try {
            ImageData imgDataCabeceraLeft = ImageDataFactory.create("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRW4g2Wr0VQijPDpbnQ-NmIpuw0lleR-Vfxmw&s");
            ImageData imgDataCabeceraRight = ImageDataFactory.create("https://upload.wikimedia.org/wikipedia/commons/6/67/UTN_logo.jpg");

            Image imgCabeceraLeft = new Image(imgDataCabeceraLeft);
            Image imgCabeceraRight = new Image(imgDataCabeceraRight);

            imgCabeceraLeft.scaleAbsolute(90f, 90f);
            imgCabeceraRight.scaleAbsolute(90f, 90f);

            imgCabeceraLeft.setFixedPosition(50, 750);
            document.add(imgCabeceraLeft);

            imgCabeceraRight.setFixedPosition(500, 750);
            document.add(imgCabeceraRight);

            addEmptyLine(document, 4);
            setLineaReporte(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}