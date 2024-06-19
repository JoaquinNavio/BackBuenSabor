package com.entidades.buenSabor.domain.dto.Empresa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaCreateDto {
    private String nombre;
    private String razonSocial;
    private Long cuil;
    private MultipartFile imagen;
}
