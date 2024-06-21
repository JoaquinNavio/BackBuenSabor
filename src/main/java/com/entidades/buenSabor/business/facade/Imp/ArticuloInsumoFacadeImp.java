package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.ArticuloInsumoFacade;
import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.service.ArticuloInsumoService;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.business.service.CloudinaryService;
import com.entidades.buenSabor.domain.dto.Insumo.ArticuloInsumoCreateDto;
import com.entidades.buenSabor.domain.dto.Insumo.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import com.entidades.buenSabor.domain.entities.Sucursal;
import com.entidades.buenSabor.repositories.ArticuloInsumoRepository;
import com.entidades.buenSabor.repositories.ImagenArticuloRepository;
import com.entidades.buenSabor.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ArticuloInsumoFacadeImp extends BaseFacadeImp<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoCreateDto, ArticuloInsumoCreateDto, Long> implements ArticuloInsumoFacade {
    public ArticuloInsumoFacadeImp(BaseService<ArticuloInsumo, Long> baseService, BaseMapper<ArticuloInsumo, ArticuloInsumoDto,ArticuloInsumoCreateDto,ArticuloInsumoCreateDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Autowired
    private ArticuloInsumoService articuloInsumoService;

    @Autowired
    private ArticuloInsumoRepository articuloInsumoRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ImagenArticuloRepository imagenArticuloRepository;

    public ResponseEntity<ArticuloInsumoDto> createCompleto(ArticuloInsumoCreateDto insumoCreateDto) {

        ArticuloInsumo articuloInsumo = baseMapper.toEntityCreate(insumoCreateDto);


        if (insumoCreateDto.getSucursal_id() != null) {
            Sucursal sucursal = sucursalRepository.findById(insumoCreateDto.getSucursal_id())
                    .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
            articuloInsumo.setSucursal(sucursal);
        }

        ArticuloInsumo savedArticuloInsumo = baseService.create(articuloInsumo);

        Set<ImagenArticulo> imagenesArticulo = new HashSet<>();
        try {
            for (MultipartFile file : insumoCreateDto.getFiles()) {

                ImagenArticulo imagenArticulo = new ImagenArticulo();
                imagenArticulo.setName(file.getOriginalFilename());
                imagenArticulo.setUrl(cloudinaryService.uploadFile(file));
                imagenArticulo.setArticulo(savedArticuloInsumo);

                // Agregar la URL a la lista de URLs
                imagenesArticulo.add(imagenArticuloRepository.save(imagenArticulo));
            };
            savedArticuloInsumo.setImagenes(imagenesArticulo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar las imagenes de articulos");
        }


            ArticuloInsumoDto articuloInsumoDto = baseMapper.toDTO(savedArticuloInsumo);
        return ResponseEntity.ok(articuloInsumoDto);
    }

    public List<ArticuloInsumoDto> getArticulosNoParaElaborar() {
        List<ArticuloInsumo> articulos = articuloInsumoRepository.findByEsParaElaborarFalse();
        return baseMapper.toDTOsList(articulos);
    }

    public List<ArticuloInsumo> getBySucursalId(Long sucursalId) {
        return articuloInsumoService.findBySucursalId(sucursalId);
    }


}