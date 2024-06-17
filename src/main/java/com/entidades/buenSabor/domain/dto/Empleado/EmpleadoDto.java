package com.entidades.buenSabor.domain.dto.Empleado;

import com.entidades.buenSabor.domain.dto.BaseDto;
import com.entidades.buenSabor.domain.entities.ImagenPersona;
import com.entidades.buenSabor.domain.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDto extends BaseDto {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Rol tipoEmpleado;
    private Long sucursal_id;
    private ImagenPersona imagen;
}
