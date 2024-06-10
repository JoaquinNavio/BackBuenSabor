package com.entidades.buenSabor.domain.enums;


public enum Estado {
    PREPARACION,
    PENDIENTE_ENTREGA_MP,  // Nuevo estado para Mercado Pago
    PENDIENTE_ENTREGA_PAGO_EFECTIVO,  // Nuevo estado para Efectivo
    CANCELADO,
    RECHAZADO,
    ENTREGADO
}