package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.ArticuloManufacturadoDetalleService;
import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import org.springframework.stereotype.Service;

/*Esta clase extiende BaseServiceImp
para proporcionar la implementación de los métodos básicos definidos en la interfaz BaseService
para la entidad ArticuloManufacturadoDetalle.*/
/* Esta clase no define métodos adicionales más allá de los heredados de BaseServiceImp.
Su objetivo principal es proporcionar operaciones básicas de CRUD para la entidad ArticuloManufacturadoDetalle*/
@Service
public class ArticuloManufacturadoDetalleServiceImp extends BaseServiceImp<ArticuloManufacturadoDetalle, Long> implements ArticuloManufacturadoDetalleService {
}
