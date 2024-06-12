package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.mapper.CategoriaMapper;
import com.entidades.buenSabor.business.mapper.UnidadMedidaMapper;
import com.entidades.buenSabor.business.service.*;
import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoCreateDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoEcommerseDto;
import com.entidades.buenSabor.domain.dto.Insumo.ImagenArticuloDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import com.entidades.buenSabor.repositories.ArticuloManufacturadoDetalleRepository;
import com.entidades.buenSabor.repositories.ArticuloManufacturadoRepository;
import com.entidades.buenSabor.repositories.ImagenArticuloRepository;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

/*BaseServiceImp:
Esta clase extiende BaseServiceImp,
que proporciona la implementación de los métodos básicos definidos en la interfaz BaseService
para la entidad ArticuloManufacturado.*/


@Service
public class ArticuloManufacturadoServiceImp extends BaseServiceImp<ArticuloManufacturado, Long> implements ArticuloManufacturadoService {

    //Inyeccion de dependencias

    /*ArticuloManufacturadoRepository:
    Se utiliza para acceder y manipular datos de la entidad ArticuloManufacturado en la base de datos.*/
    @Autowired
    private ArticuloManufacturadoRepository baseRepository;

    /*ArticuloManufacturadoDetalleRepository:
    Permite acceder y manipular datos de los detalles del artículo manufacturado en la base de datos.*/
    @Autowired
    private ArticuloManufacturadoDetalleRepository detalleRepository;

    //Idem
    @Autowired
    private UnidadMedidaService unidadMedidaService;

    //Idem
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ImagenArticuloService imagenArticuloService;

    @Autowired
    private ImagenArticuloRepository imagenArticuloRepository;

    //Idem
    @Autowired
    private ArticuloInsumoService articuloInsumoService;

    @Autowired
    private UnidadMedidaMapper unidadMedidaMapper;

    @Autowired
    private CategoriaMapper categoriaMapper;

    /*getDetallesById(Long id):
    Este método devuelve una lista de detalles de artículo manufacturado
    asociados a un artículo manufacturado específico identificado por su ID.*/
    @Override
    public List<ArticuloManufacturadoDetalle> getDetallesById(Long id) {
        System.out.println("Ejecutando getDetallesById(Long id) - ArticuloManufacturadoServiceImp");
        System.out.println("Obteniendo entidades con findDetallesById(id) de ArticuloManufacturadoRepository - ArticuloManufacturadoServiceImp");
        //Los devuelve
        return baseRepository.findDetallesById(id);
    }


    /*createWithDetails(ArticuloManufacturadoCreateDto dto):
    Crea un nuevo artículo manufacturado con los detalles proporcionados en un DTO de creación.
    Guarda tanto el artículo manufacturado como sus detalles en la base de datos.*/
    @Override
    /*@Transactional:
    La anotación @Transactional asegura que si cualquier excepción no verificada ocurre dentro del método anotado,
    se desencadenará un rollback automático, revirtiendo todas las operaciones realizadas
    dentro de ese método hasta el momento de la excepción.*/
    @Transactional
    public ArticuloManufacturado createWithDetails(ArticuloManufacturadoCreateDto dto) {
        System.out.println("EJECUTANDO createWithDetails(ArticuloManufacturadoCreateDto dto) - ArticuloManufacturadoServiceImp");

        // Crear ArticuloManufacturado
        ArticuloManufacturado articuloManufacturado = new ArticuloManufacturado();
        System.out.println("Creo ArticuloManufactuado y seteo los datos del DTO - ArticuloManufacturadoServiceImp");
        articuloManufacturado.setDenominacion(dto.getDenominacion());
        articuloManufacturado.setDescripcion(dto.getDescripcion());
        articuloManufacturado.setTiempoEstimadoMinutos(dto.getTiempoEstimadoMinutos());
        articuloManufacturado.setPrecioVenta(dto.getPrecioVenta());
        articuloManufacturado.setPreparacion(dto.getPreparacion());
        articuloManufacturado.setUnidadMedida(unidadMedidaService.getById(dto.getIdUnidadMedida()));
        articuloManufacturado.setCategoria(categoriaService.getById(dto.getIdCategoria()));
        // Guardar ArticuloManufacturado
        System.out.println("Guardando ArticuloManufacturado con save(articuloManufacturado) de ArticuloManufacturadoRepository - ArticuloManufacturadoServiceImp");
        ArticuloManufacturado savedArticuloManufacturado = baseRepository.save(articuloManufacturado);

        Set<ImagenArticulo> imagenesArticulo = new HashSet<>();
        try {
            for (MultipartFile file : dto.getFiles()) {

                ImagenArticulo imagenArticulo = new ImagenArticulo();
                imagenArticulo.setName(file.getOriginalFilename());
                imagenArticulo.setUrl(cloudinaryService.uploadFile(file));
                ArticuloManufacturado articuloManufacturadoo = new ArticuloManufacturado();
                articuloManufacturadoo.setId(articuloManufacturado.getId());
                imagenArticulo.setArticulo(articuloManufacturadoo);

                // Agregar la URL a la lista de URLs
                imagenesArticulo.add(imagenArticuloRepository.save(imagenArticulo));
            };
            articuloManufacturado.setImagenes(imagenesArticulo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar las imagenes de articulos");
        }

        List<ArticuloManufacturadoDetalle> detalles = dto.getDetalles().stream().map(detalleDto -> {
            ArticuloManufacturadoDetalle detalle = new ArticuloManufacturadoDetalle();
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setArticuloInsumo(articuloInsumoService.getById(detalleDto.getIdArticuloInsumo()));
            detalle.setArticuloManufacturado(articuloManufacturado);
            return detalle;
        }).collect(Collectors.toList());

        detalleRepository.saveAll(detalles);
        return savedArticuloManufacturado;
    }

    @Override
    public ArticuloManufacturado vincularImagenes(MultipartFile[] files, Long id) {
        imagenArticuloService.vincularImagenesArticulo(files, id);
        baseRepository.flush();
        return baseRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el articuloManufacturado con id: " + id));
    }

    @Override
    @Transactional
    public ArticuloManufacturado updateWithDetails(Long id, ArticuloManufacturadoCreateDto dto) {
        System.out.println("EJECUTANDO updateWithDetails(Long id, ArticuloManufacturadoCreateDto dto) - ArticuloManufacturadoServiceImp");

        // Obtener el artículo manufacturado existente por su ID
        System.out.println("Obteniendo el ArticuloManufacturado con findById(id) de ArticuloManufacturadoRepository - ArticuloManufacturadoServiceImp");
        ArticuloManufacturado existingArticuloManufacturado = baseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ArticuloManufacturado not found with id: " + id));

        // Actualizar los atributos del artículo manufacturado con los nuevos valores del DTO
        System.out.println("Actualizando los cambios del dto - ArticuloManufacturadoServiceImp");
        existingArticuloManufacturado.setDenominacion(dto.getDenominacion());
        existingArticuloManufacturado.setDescripcion(dto.getDescripcion());
        existingArticuloManufacturado.setTiempoEstimadoMinutos(dto.getTiempoEstimadoMinutos());
        existingArticuloManufacturado.setPrecioVenta(dto.getPrecioVenta());
        existingArticuloManufacturado.setPreparacion(dto.getPreparacion());
        existingArticuloManufacturado.setUnidadMedida(unidadMedidaService.getById(dto.getIdUnidadMedida()));
        existingArticuloManufacturado.setCategoria(categoriaService.getById(dto.getIdCategoria()));

        /*Long idImageToDelete = null;
        if (existingArticuloManufacturado.getImage() != null && !existingArticuloManufacturado.getImage().getId().equals(dto.getIdImage())) {
            idImageToDelete = existingArticuloManufacturado.getImage().getId();
        }
        if (dto.getIdImage() != null) {
            Image image = new Image();
            image.setId(dto.getIdImage());
            existingArticuloManufacturado.setImage(image);
        } else {
            existingArticuloManufacturado.setImage(null);
        }*/

        // Guardar los cambios en el artículo manufacturado
        System.out.println("Guardando los cambios con save(existingArticuloManufacturado) de ArticuloManufacturadoRepository - ArticuloManufacturadoServiceImp ");
        ArticuloManufacturado updatedArticuloManufacturado = baseRepository.save(existingArticuloManufacturado);


        /// Obtener los detalles existentes del artículo manufacturado
        System.out.println("Obteniendo los detalles del ArticuloManufacturado - ArticuloManufacturadoServiceImp ");
        Map<Long, ArticuloManufacturadoDetalle> existingDetallesMap = existingArticuloManufacturado.getDetalles().stream()
                .collect(Collectors.toMap(ArticuloManufacturadoDetalle::getId, detalle -> detalle));


        // Actualizar y crear los detalles del artículo manufacturado
        System.out.println("Actualizando los detalles del ArticuloManufacturado - ArticuloManufacturadoServiceImp ");
        List<ArticuloManufacturadoDetalle> nuevosDetalles = dto.getDetalles().stream().map(detalleDto -> {
            ArticuloManufacturadoDetalle detalle;
            if (detalleDto.getIdDetalle() != null && existingDetallesMap.containsKey(detalleDto.getIdDetalle())) {
                // Actualizar el detalle existente
                detalle = existingDetallesMap.get(detalleDto.getIdDetalle());
            } else {
                // Crear un nuevo detalle
                detalle = new ArticuloManufacturadoDetalle();
                detalle.setArticuloManufacturado(updatedArticuloManufacturado);
            }
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setArticuloInsumo(articuloInsumoService.getById(detalleDto.getIdArticuloInsumo()));
            return detalle;
        }).collect(Collectors.toList());

        // Guardar los detalles actualizados y nuevos
        System.out.println("Guardando detalles - ArticuloManufacturadoServiceImp");
        detalleRepository.saveAll(nuevosDetalles);
        System.out.println("Retornando detalles - ArticuloManufacturadoServiceImp");
        return updatedArticuloManufacturado;
    }

    @Override
    public List<ArticuloManufacturadoEcommerseDto> getManufacturadosEcommerse() {
        List<ArticuloManufacturado> articulosManufacturados = baseRepository.getAll();
        List<ArticuloManufacturadoEcommerseDto> articuloEcommerseDtos = new ArrayList<>();

        for (ArticuloManufacturado articulo : articulosManufacturados) {
            ArticuloManufacturadoEcommerseDto articuloEcommerse = new ArticuloManufacturadoEcommerseDto();
            articuloEcommerse.setId(articulo.getId());
            articuloEcommerse.setDenominacion(articulo.getDenominacion());
            articuloEcommerse.setDescripcion(articulo.getDescripcion());
            articuloEcommerse.setTiempoEstimadoMinutos(articulo.getTiempoEstimadoMinutos());
            articuloEcommerse.setPrecioVenta(articulo.getPrecioVenta());
            articuloEcommerse.setPreparacion(articulo.getPreparacion());
            System.out.println("Llega hasta acá");

            articuloEcommerse.setImagenes(getImagenesDto(articulo.getImagenes()));
            System.out.println("converti las imagenes a dto");
            articuloEcommerse.setUnidadMedida(unidadMedidaMapper.toDTO(articulo.getUnidadMedida()));
            articuloEcommerse.setCategoria(categoriaMapper.toDTO(articulo.getCategoria()));
            System.out.println("converti las demas entidades a dto");
            List<ArticuloManufacturadoDetalle> detalles = baseRepository.findDetallesById(articulo.getId());
            articuloEcommerse.setStock(getStock(detalles));

            articuloEcommerseDtos.add(articuloEcommerse);
        }

        return articuloEcommerseDtos;
    }

    private Integer getStock(List<ArticuloManufacturadoDetalle> detalles) {
        Integer stock = 0;
        boolean primeraVuelta = true;

        for (ArticuloManufacturadoDetalle detalle : detalles) {
            if (primeraVuelta) {
                stock = detalle.getArticuloInsumo().getStockActual();
                primeraVuelta = false;
            } else {
                if (detalle.getArticuloInsumo().getStockActual() < stock) {
                    stock = detalle.getArticuloInsumo().getStockActual();
                }
            }
        }
        return stock;
    }

    private Set<ImagenArticuloDto> getImagenesDto(Set<ImagenArticulo> imagenes){
        Set<ImagenArticuloDto> imagenesDto = new HashSet<>();
        for (ImagenArticulo imagen : imagenes) {
            ImagenArticuloDto imagenDto = new ImagenArticuloDto();
            imagenDto.setId(imagen.getId());
            imagenDto.setUrl(imagen.getUrl());
            imagenesDto.add(imagenDto);
        }
        return imagenesDto;
    }

}