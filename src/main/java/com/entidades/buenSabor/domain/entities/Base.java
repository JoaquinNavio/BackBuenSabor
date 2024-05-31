package com.entidades.buenSabor.domain.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.io.Serializable;

/*@MappedSuperclass: Indica que esta clase no será una entidad en sí misma,
 pero que sus propiedades serán heredadas por las entidades que la extiendan.*/
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Setter
@SuperBuilder
public abstract class Base implements Serializable {

    /*@Id:Marca el campo como la clave primaria de la entidad.
    En JPA, cada entidad debe tener una clave primaria,
    que sirve como identificador único en la base de datos.*/
    @Id
    /*@GeneratedValue(strategy = GenerationType.IDENTITY):
    Indica que el valor del campo será generado automáticamente por la base de datos al insertar una nueva fila.
    GenerationType.IDENTITY es una estrategia de generación que utiliza una columna auto-incremental
    en la base de datos para asignar valores únicos a la clave primaria.
    Esto significa que la base de datos se encargará de generar el valor del campo id cuando se inserte un nuevo registro.*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected boolean eliminado = false;

    public Boolean isEliminado(){
        return eliminado;
    }
}

