package com.entidades.buenSabor.domain.dto.Empleado;

import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioCreateDto;
import com.entidades.buenSabor.domain.enums.Rol;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmpleadoCreateDto {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Rol tipoEmpleado;
    private Long sucursal_id;
    private MultipartFile imagen;

}
