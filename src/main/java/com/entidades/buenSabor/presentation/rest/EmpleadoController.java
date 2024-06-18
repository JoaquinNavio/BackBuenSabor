package com.entidades.buenSabor.presentation.rest;


import com.entidades.buenSabor.business.facade.Imp.ArticuloManufacturadoFacadeImp;
import com.entidades.buenSabor.business.facade.Imp.EmpleadoFacadeImp;
import com.entidades.buenSabor.business.facade.Imp.EmpresaFacadeImpl;
import com.entidades.buenSabor.business.mapper.EmpleadoMapper;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleDto;
import com.entidades.buenSabor.domain.dto.Empleado.EmpleadoCreateDto;
import com.entidades.buenSabor.domain.dto.Empleado.EmpleadoDto;
import com.entidades.buenSabor.domain.dto.Empresa.EmpresaCreateDto;
import com.entidades.buenSabor.domain.dto.Empresa.EmpresaDto;
import com.entidades.buenSabor.domain.entities.Empleado;
import com.entidades.buenSabor.domain.entities.Empresa;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleado")
@CrossOrigin("*")
public class EmpleadoController extends BaseControllerImp<Empleado, EmpleadoDto, EmpleadoCreateDto, EmpleadoCreateDto,Long, EmpleadoFacadeImp> {

    public EmpleadoController(EmpleadoFacadeImp facade) {
        super(facade);
    }

    @Autowired
    EmpleadoMapper empleadoMapper;

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(facade.findById(id));
    }

    @GetMapping("/sucursal/{id}")
    public ResponseEntity<List<EmpleadoDto>> getEmpleadosBySucursal(@PathVariable Long id) {
        List<Empleado> empleados = facade.getEmpleadosBySucursal(id);
        List<EmpleadoDto> empleadoDtos = empleadoMapper.toDTOsList(empleados);
        return ResponseEntity.ok(empleadoDtos);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EmpleadoDto> create(@ModelAttribute EmpleadoCreateDto empleadoDto) {
        return ResponseEntity.ok(facade.create(empleadoDto));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<EmpleadoDto> getByEmail(@PathVariable String email) {
        Optional<EmpleadoDto> empleadoOptional = facade.getByEmail(email);
        return empleadoOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
