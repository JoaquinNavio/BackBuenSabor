package com.entidades.buenSabor.business.service.Imp;

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
import com.entidades.buenSabor.domain.entities.Sucursal;
import com.entidades.buenSabor.repositories.ArticuloManufacturadoDetalleRepository;
import com.entidades.buenSabor.repositories.ArticuloManufacturadoRepository;
import com.entidades.buenSabor.repositories.ImagenArticuloRepository;
import com.entidades.buenSabor.repositories.SucursalRepository;
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

    @Autowired
    private SucursalRepository sucursalRepository;

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
        ArticuloManufacturado articuloManufacturado = new ArticuloManufacturado();
        articuloManufacturado.setDenominacion(dto.getDenominacion());
        articuloManufacturado.setDescripcion(dto.getDescripcion());
        articuloManufacturado.setTiempoEstimadoMinutos(dto.getTiempoEstimadoMinutos());
        articuloManufacturado.setPrecioVenta(dto.getPrecioVenta());
        articuloManufacturado.setPreparacion(dto.getPreparacion());
        articuloManufacturado.setUnidadMedida(unidadMedidaService.getById(dto.getIdUnidadMedida()));
        articuloManufacturado.setCategoria(categoriaService.getById(dto.getIdCategoria()));

        if (dto.getSucursal_id() != null) {
            Sucursal sucursal = sucursalRepository.findById(dto.getSucursal_id())
                    .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
            articuloManufacturado.setSucursal(sucursal);
        }

        ArticuloManufacturado savedArticuloManufacturado = baseRepository.save(articuloManufacturado);

        Set<ImagenArticulo> imagenesArticulo = new HashSet<>();
        try {
            for (MultipartFile file : dto.getFiles()) {
                ImagenArticulo imagenArticulo = new ImagenArticulo();
                imagenArticulo.setName(file.getOriginalFilename());
                imagenArticulo.setUrl(cloudinaryService.uploadFile(file));
                imagenArticulo.setArticulo(savedArticuloManufacturado);
                imagenesArticulo.add(imagenArticuloRepository.save(imagenArticulo));
            }
            savedArticuloManufacturado.setImagenes(imagenesArticulo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar las imágenes de artículos");
        }

        List<ArticuloManufacturadoDetalle> detalles = dto.getDetalles().stream().map(detalleDto -> {
            ArticuloManufacturadoDetalle detalle = new ArticuloManufacturadoDetalle();
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setArticuloInsumo(articuloInsumoService.getById(detalleDto.getIdArticuloInsumo()));
            detalle.setArticuloManufacturado(savedArticuloManufacturado);
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
        ArticuloManufacturado existingArticuloManufacturado = baseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ArticuloManufacturado not found with id: " + id));

        existingArticuloManufacturado.setDenominacion(dto.getDenominacion());
        existingArticuloManufacturado.setDescripcion(dto.getDescripcion());
        existingArticuloManufacturado.setTiempoEstimadoMinutos(dto.getTiempoEstimadoMinutos());
        existingArticuloManufacturado.setPrecioVenta(dto.getPrecioVenta());
        existingArticuloManufacturado.setPreparacion(dto.getPreparacion());
        existingArticuloManufacturado.setUnidadMedida(unidadMedidaService.getById(dto.getIdUnidadMedida()));
        existingArticuloManufacturado.setCategoria(categoriaService.getById(dto.getIdCategoria()));

        if (dto.getSucursal_id() != null) {
            Sucursal sucursal = sucursalRepository.findById(dto.getSucursal_id())
                    .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
            existingArticuloManufacturado.setSucursal(sucursal);
        }

        ArticuloManufacturado updatedArticuloManufacturado = baseRepository.save(existingArticuloManufacturado);

        Map<Long, ArticuloManufacturadoDetalle> existingDetallesMap = existingArticuloManufacturado.getDetalles().stream()
                .collect(Collectors.toMap(ArticuloManufacturadoDetalle::getId, detalle -> detalle));

        List<ArticuloManufacturadoDetalle> nuevosDetalles = dto.getDetalles().stream().map(detalleDto -> {
            ArticuloManufacturadoDetalle detalle;
            if (detalleDto.getIdDetalle() != null && existingDetallesMap.containsKey(detalleDto.getIdDetalle())) {
                detalle = existingDetallesMap.get(detalleDto.getIdDetalle());
            } else {
                detalle = new ArticuloManufacturadoDetalle();
                detalle.setArticuloManufacturado(updatedArticuloManufacturado);
            }
            detalle.setCantidad(detalleDto.getCantidad());
            detalle.setArticuloInsumo(articuloInsumoService.getById(detalleDto.getIdArticuloInsumo()));
            return detalle;
        }).collect(Collectors.toList());

        detalleRepository.saveAll(nuevosDetalles);
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

            articuloEcommerse.setImagenes(getImagenesDto(articulo.getImagenes()));

            articuloEcommerse.setUnidadMedida(unidadMedidaMapper.toDTO(articulo.getUnidadMedida()));
            articuloEcommerse.setCategoria(categoriaMapper.toDTO(articulo.getCategoria()));

            List<ArticuloManufacturadoDetalle> detalles = baseRepository.findDetallesById(articulo.getId());
            articuloEcommerse.setStock(getStock(detalles));

            articuloEcommerseDtos.add(articuloEcommerse);
        }

        return articuloEcommerseDtos;
    }

    private Integer getStock(List<ArticuloManufacturadoDetalle> detalles) {
        List<Integer> stock = new ArrayList<>();

        for (ArticuloManufacturadoDetalle detalle : detalles) {
            if (detalle.getArticuloInsumo().getStockActual() < detalle.getCantidad()) {
                return 0;
            }
            stock.add(Math.floorDiv(detalle.getArticuloInsumo().getStockActual(), detalle.getCantidad()));
        }

        if (stock.isEmpty()) {
            return 0;
        }

        // Retornar el número más bajo del stock
        return Collections.min(stock);
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

    @Override
    public List<ArticuloManufacturado> findBySucursalId(Long sucursalId) {
        return baseRepository.findBySucursalId(sucursalId);
    }

}