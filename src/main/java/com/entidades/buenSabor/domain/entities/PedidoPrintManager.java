package com.entidades.buenSabor.domain.entities;

import com.entidades.buenSabor.business.service.EmailService;
import com.entidades.buenSabor.business.service.PedidoService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.mail.EmailException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class PedidoPrintManager {

    private final PedidoService pedidoService;
    private final EmailService emailService;

    @Autowired
    public PedidoPrintManager(PedidoService pedidoService, EmailService emailService) {
        this.pedidoService = pedidoService;
        this.emailService = emailService;
    }

    public SXSSFWorkbook imprimirExcelPedidos(List<Pedido> pedidos) {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet("Pedidos");

        // Crear el encabezado
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID Pedido");
        header.createCell(1).setCellValue("Fecha Pedido");
        header.createCell(2).setCellValue("Total Pedido");
        header.createCell(3).setCellValue("Denominación Artículo");
        header.createCell(4).setCellValue("Cantidad");
        header.createCell(5).setCellValue("SubTotal");
        header.createCell(6).setCellValue("Nombre Cliente");
        header.createCell(7).setCellValue("Email Cliente");
        header.createCell(8).setCellValue("ID Factura");

        // Llenar los datos
        int rowNum = 1;
        for (Pedido pedido : pedidos) {
            boolean isFirstDetail = true;
            for (DetallePedido detalle : pedido.getDetallePedidos()) {
                Row row = sheet.createRow(rowNum++);
                if (isFirstDetail) {
                    row.createCell(0).setCellValue(pedido.getId());
                    row.createCell(1).setCellValue(pedido.getFechaPedido().toString());
                    row.createCell(2).setCellValue(pedido.getTotal());
                    row.createCell(6).setCellValue(pedido.getUser().getNombre());
                    row.createCell(7).setCellValue(pedido.getUser().getGmail());
                    row.createCell(8).setCellValue(pedido.getFactura() != null ? String.valueOf(pedido.getFactura().getId()) : "");
                    isFirstDetail = false;
                } else {
                    row.createCell(0).setCellValue("");
                    row.createCell(1).setCellValue("");
                    row.createCell(2).setCellValue("");
                    row.createCell(6).setCellValue("");
                    row.createCell(7).setCellValue("");
                    row.createCell(8).setCellValue("");
                }
                row.createCell(3).setCellValue(detalle.getArticulo().getDenominacion());
                row.createCell(4).setCellValue(detalle.getCantidad());
                row.createCell(5).setCellValue(detalle.getSubTotal());
            }
        }

        return workbook;
    }

    public void enviarExcelPorEmail(List<Pedido> pedidos, String to, String subject, String text) throws EmailException, IOException {
        SXSSFWorkbook workbook = imprimirExcelPedidos(pedidos);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        workbook.close(); // Cierra el workbook después de escribir
        bos.close();

        emailService.sendEmailWithAttachment(to, subject, text, bos, "Pedidos.xlsx");
    }
}