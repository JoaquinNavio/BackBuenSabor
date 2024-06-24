package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.DomicilioFacadeImp;
import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioCreateDto;
import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioDto;
import com.entidades.buenSabor.domain.entities.Domicilio;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import com.entidades.buenSabor.repositories.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/domicilio")
@CrossOrigin("*")
public class DomicilioController extends BaseControllerImp<Domicilio, DomicilioDto, DomicilioCreateDto,DomicilioCreateDto,Long, DomicilioFacadeImp> {
    @Autowired
    private DomicilioRepository domicilioRepository;
    public DomicilioController(DomicilioFacadeImp facade) {
        super(facade);
    }

}
