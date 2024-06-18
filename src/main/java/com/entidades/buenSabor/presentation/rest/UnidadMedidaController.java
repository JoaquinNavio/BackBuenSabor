package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.UnidadMedidaFacadeImp;
import com.entidades.buenSabor.business.mapper.UnidadMedidaMapper;
import com.entidades.buenSabor.business.service.UnidadMedidaService;
import com.entidades.buenSabor.domain.dto.UnidadMedida.UnidadMedidaCreateDto;
import com.entidades.buenSabor.domain.dto.UnidadMedida.UnidadMedidaDto;
import com.entidades.buenSabor.domain.entities.UnidadMedida;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/UnidadMedida")
@CrossOrigin("*")
public class UnidadMedidaController extends BaseControllerImp<UnidadMedida, UnidadMedidaDto, UnidadMedidaCreateDto, UnidadMedidaCreateDto,Long, UnidadMedidaFacadeImp> {
    public UnidadMedidaController(UnidadMedidaFacadeImp facade) {
        super(facade);
    }

    @Autowired
    UnidadMedidaMapper unidadMedidaMapper;

    @Autowired
    UnidadMedidaService unidadMedidaService;

    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<UnidadMedidaDto>> getBySucursalId(@PathVariable Long sucursalId) {
        List<UnidadMedida> unidades = unidadMedidaService.findBySucursalId(sucursalId);
        List<UnidadMedidaDto> unidadMedidaDtos = unidades.stream()
                .map(unidadMedidaMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(unidadMedidaDtos);
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('Cocinero') or hasAuthority('Admin')")
    @Override
    public ResponseEntity<UnidadMedidaDto> create(@RequestBody UnidadMedidaCreateDto entity) {
        return ResponseEntity.ok(facade.createNew(entity));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Cocinero') or hasAuthority('Admin')")
    @Override
    public ResponseEntity<UnidadMedidaDto> edit(@RequestBody UnidadMedidaCreateDto entity, @PathVariable Long id) {
        return ResponseEntity.ok(facade.update(entity, id));
    }
}
