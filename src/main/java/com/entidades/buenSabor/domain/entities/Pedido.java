package com.entidades.buenSabor.domain.entities;


import com.entidades.buenSabor.domain.enums.Estado;
import com.entidades.buenSabor.domain.enums.FormaPago;
import com.entidades.buenSabor.domain.enums.TipoEnvio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder
@JsonIgnoreProperties({"detallePedidos"}) // Ignorar relaciones recursivas

public class Pedido extends Base {

    private LocalTime horaEstimadaFinalizacion;
    private Double total;
    private Double totalCosto;
    private Estado estado;
    private TipoEnvio tipoEnvio;
    private FormaPago formaPago;
    private LocalDate fechaPedido;

    @ManyToOne
    private Domicilio domicilio;

    @ManyToOne
    private Sucursal sucursal;

    @OneToOne
    private Factura factura;

    @Column(name = "cliente_id")
    private int clienteId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    @Builder.Default
    private Set<DetallePedido> detallePedidos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;
}

