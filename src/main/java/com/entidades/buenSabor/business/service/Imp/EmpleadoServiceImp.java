package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.CloudinaryService;
import com.entidades.buenSabor.business.service.EmpleadoService;
import com.entidades.buenSabor.domain.entities.Domicilio;
import com.entidades.buenSabor.domain.entities.Empleado;
import com.entidades.buenSabor.domain.entities.ImagenPersona;
import com.entidades.buenSabor.repositories.DomicilioRepository;
import com.entidades.buenSabor.repositories.EmpleadoRepository;
import com.entidades.buenSabor.repositories.ImagenPersonaRepository;
import com.entidades.buenSabor.repositories.LocalidadRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmpleadoServiceImp extends BaseServiceImp<Empleado, Long> implements EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    LocalidadRepository localidadRepository;
    @Autowired
    DomicilioRepository domicilioRepository;

    @Autowired
    ImagenPersonaRepository imagenPersonaRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public List<Empleado> getEmpleadoBySucursal(Long idSucursal) {
        List<Empleado> empleados = empleadoRepository.findBySucursalId(idSucursal);
        return empleados;
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


    @Transactional
    public Empleado update(Long id, Empleado empleado, MultipartFile imagenFile) {
        try {
            Empleado existingEmpleado = empleadoRepository.findById(id).orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

            // Actualiza los campos del empleado
            existingEmpleado.setNombre(empleado.getNombre());
            existingEmpleado.setApellido(empleado.getApellido());
            existingEmpleado.setEmail(empleado.getEmail());
            existingEmpleado.setTelefono(empleado.getTelefono());
            existingEmpleado.setTipoEmpleado(empleado.getTipoEmpleado());


            // Maneja la imagen del empleado
            if (imagenFile != null && !imagenFile.isEmpty()) {
                String imageUrl = cloudinaryService.uploadFile(imagenFile);
                ImagenPersona imagen = new ImagenPersona();
                imagen.setUrl(imageUrl);
                imagenPersonaRepository.save(imagen);
                existingEmpleado.setImagen(imagen);
            }

            // Guarda el empleado actualizado
            return empleadoRepository.save(existingEmpleado);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el empleado");
        }
    }
}
