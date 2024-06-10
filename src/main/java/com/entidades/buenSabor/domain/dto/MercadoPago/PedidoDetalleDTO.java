package com.entidades.buenSabor.domain.dto.MercadoPago;

public class PedidoDetalleDTO {
    private Long instrumentoId;
    private Long pedidoId;
    private int cantidad;

    // Getters and setters
    public Long getInstrumentoId() {
        return instrumentoId;
    }

    public void setInstrumentoId(Long instrumentoId) {
        this.instrumentoId = instrumentoId;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
