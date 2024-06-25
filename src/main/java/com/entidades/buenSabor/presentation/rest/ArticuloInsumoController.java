package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.ArticuloInsumoFacadeImp;
import com.entidades.buenSabor.business.mapper.ArticuloInsumoMapper;
import com.entidades.buenSabor.domain.dto.Insumo.ArticuloInsumoCreateDto;
import com.entidades.buenSabor.domain.dto.Insumo.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/ArticuloInsumo")
public class ArticuloInsumoController extends BaseControllerImp<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoCreateDto, ArticuloInsumoCreateDto, Long, ArticuloInsumoFacadeImp> {

    public ArticuloInsumoController(ArticuloInsumoFacadeImp facade) {
        super(facade);
    }

    @Autowired
    private ArticuloInsumoMapper articuloInsumoMapper;

    @PreAuthorize("hasAuthority('Cocinero') or hasAuthority('Admin')")
    @PostMapping("/crearCompleto")
    //@PreAuthorize("hasAuthority('Cocinero') or hasAuthority('Admin')")
    public ResponseEntity<ArticuloInsumoDto> createCompleto(@ModelAttribute ArticuloInsumoCreateDto articuloDto) {
        System.out.println(articuloDto.toString());
        System.out.println("INICIO CREATE Articulo Insumo - BaseController");
        return facade.createCompleto(articuloDto);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('Cocinero') or hasAuthority('Admin')")
    @Override
    public ResponseEntity<ArticuloInsumoDto> create(@RequestBody ArticuloInsumoCreateDto entity) {
        return ResponseEntity.ok(facade.createNew(entity));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Cocinero') or hasAuthority('Admin')")
    @Override
    public ResponseEntity<ArticuloInsumoDto> edit(@RequestBody ArticuloInsumoCreateDto entity, @PathVariable Long id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }
        return ResponseEntity.ok(facade.update(entity, id));
    }

    @GetMapping("/noElaborar")
    public ResponseEntity<List<ArticuloInsumoDto>> getArticulosNoParaElaborar() {
        List<ArticuloInsumoDto> articulos = facade.getArticulosNoParaElaborar();
        return ResponseEntity.ok(articulos);
    }

    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<ArticuloInsumoDto>> getBySucursalId(@PathVariable Long sucursalId) {
        List<ArticuloInsumo> articulos = facade.getBySucursalId(sucursalId);
        List<ArticuloInsumoDto> articulosDto = articulos.stream()
                .map(articuloInsumoMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(articulosDto);
    }
}
