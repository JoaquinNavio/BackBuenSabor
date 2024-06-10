package com.entidades.buenSabor.domain.dto.MercadoPago;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoMP {
    private long id;
    private String titulo;
    private String montoTotal;
    private Set<String> items;
}
