package com.entidades.buenSabor.domain.entities;

import jakarta.persistence.*;
import java.util.UUID;


import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images")
public class Image extends Base{

    @Column(name = "name_image")
    private String name;


    @Column(name = "url_image")
    private String url;

}

