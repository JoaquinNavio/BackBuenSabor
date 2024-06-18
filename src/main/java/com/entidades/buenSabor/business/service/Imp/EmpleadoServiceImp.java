package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.CloudinaryService;
import com.entidades.buenSabor.business.service.EmpleadoService;
import com.entidades.buenSabor.domain.entities.Empleado;
import com.entidades.buenSabor.domain.entities.ImagenPersona;
import com.entidades.buenSabor.domain.entities.Sucursal;
import com.entidades.buenSabor.repositories.EmpleadoRepository;
import com.entidades.buenSabor.repositories.ImagenPersonaRepository;
import com.entidades.buenSabor.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImp extends BaseServiceImp<Empleado,Long> implements EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    ImagenPersonaRepository imagenPersonaRepository;

    @Autowired
    SucursalRepository sucursalRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public List<Empleado> getEmpleadoBySucursal(Long idSucursal){
        return empleadoRepository.findBySucursalId(idSucursal);
    }
    public Optional<Empleado> getByEmail(String email) {
        return empleadoRepository.findByEmail(email);
    }
    @Override
    public Optional<Empleado> findById(Long id) {
        return empleadoRepository.findById(id);
    }

    @Override
    public Empleado create(Empleado empleado, MultipartFile imagenFile) {
        try {
            if (imagenFile != null && !imagenFile.isEmpty()) {
                String imageUrl = cloudinaryService.uploadFile(imagenFile);
                ImagenPersona imagen = new ImagenPersona();
                imagen.setUrl(imageUrl);

                // Guardar la imagen en la base de datos
                ImagenPersona imagenSaved = imagenPersonaRepository.save(imagen);
                empleado.setImagen(imagenSaved);
            }
            return empleadoRepository.save(empleado);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar la imagen del empleado");
        }
    }
}
