package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.CategoriaFacadeImp;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaCreateDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;
import com.entidades.buenSabor.domain.entities.Categoria;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoriaController extends BaseControllerImp<Categoria, CategoriaDto, CategoriaCreateDto, CategoriaCreateDto, Long, CategoriaFacadeImp> {

    public CategoriaController(CategoriaFacadeImp facade) {
        super(facade);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<CategoriaDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(facade.getById(id));
    }

    /*Enpoint para retornar las categorias que no son insumo*/
    @GetMapping("/NoInsumo")
    public ResponseEntity<List<CategoriaDto>> getNoInsumo() {
        return ResponseEntity.ok(facade.getAllNoElaborar());
    }


    @GetMapping
    @Override
    public ResponseEntity<List<CategoriaDto>> getAll() {
        return ResponseEntity.ok(facade.getAll());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('Cocinero') or hasAuthority('Admin')")
    @Override
    public ResponseEntity<CategoriaDto> create(@RequestBody CategoriaCreateDto entity) {
        return ResponseEntity.ok(facade.createNew(entity));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Cocinero') or hasAuthority('Admin')")
    @Override
    public ResponseEntity<CategoriaDto> edit(@RequestBody CategoriaCreateDto entity, @PathVariable Long id) {
        return ResponseEntity.ok(facade.update(entity, id));
    }
}
