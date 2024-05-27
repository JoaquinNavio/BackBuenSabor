package com.entidades.buenSabor.domain.dto.Empresa;

import com.entidades.buenSabor.domain.dto.Base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmpresaDto extends BaseDto {

    private String nombre;
    private String razonSocial;
    private Long cuil;

}

