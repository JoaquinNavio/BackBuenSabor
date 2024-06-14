package com.entidades.buenSabor.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(exclude = "articulo") // Excluir la relación recursiva
@SuperBuilder
@JsonIgnoreProperties({"articulo"}) // Ignorar relaciones recursivas
//No se audita
public class ImagenArticulo extends Base {

    @Column(name = "name_image")
    private String name;

    @Column(name = "url_image")
    private String url;

    @ManyToOne
    @JoinColumn(name = "articulo_id")
    @JsonBackReference // Agregar esta anotación
    private Articulo articulo;
}
