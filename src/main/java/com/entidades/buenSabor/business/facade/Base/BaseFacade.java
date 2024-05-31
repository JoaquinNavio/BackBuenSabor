package com.entidades.buenSabor.business.facade.Base;

import com.entidades.buenSabor.domain.dto.BaseDto;

import java.io.Serializable;
import java.util.List;
/*El patrón de diseño Facade se utiliza
para proporcionar una interfaz unificada y simplificada a un conjunto más grande de funcionalidades o servicios.
Los facades ocultan la complejidad del sistema subyacente
y permiten a los clientes interactuar con él de manera más simple y directa.
Los facades se utilizan para abstraer las operaciones sobre entidades del sistema,
como crear, obtener, actualizar y eliminar,
ofreciendo una interfaz coherente y fácil de usar para estas operaciones.*/


/*BaseFacade:
Esta interfaz define las operaciones básicas que se pueden realizar en cualquier entidad del sistema.
Incluye métodos como createNew, getById, getAll, deleteById y update.*/
public interface BaseFacade <D extends BaseDto, DC, DE, ID extends Serializable>{
    public D createNew(DC request);
    public D getById(Long id);
    public List<D> getAll();
    public void deleteById(Long id);
    public D update(DE request, Long id);
}
