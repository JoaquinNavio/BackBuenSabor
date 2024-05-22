package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.CategoriaService;
import com.entidades.buenSabor.domain.entities.Categoria;
import com.entidades.buenSabor.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoriaServiceImp extends BaseServiceImp<Categoria, Long> implements CategoriaService {
    @Override
    public Categoria update(Categoria request, Long id) {
        var optionalEntity = baseRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            throw new RuntimeException("No se encontró una entidad con el id " + id);
        }
        var existingEntity = optionalEntity.get();
        existingEntity.setDenominacion(request.getDenominacion());
        existingEntity.setEsInsumo(request.isEsInsumo());
        existingEntity.setCategoriaPadre(request.getCategoriaPadre()); // Asegúrate de actualizar la categoría padre
        return baseRepository.save(existingEntity);
    }
}
