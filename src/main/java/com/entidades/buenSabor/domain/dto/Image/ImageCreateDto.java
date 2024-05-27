package com.entidades.buenSabor.domain.dto.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImageCreateDto {
    private String name;
    private MultipartFile file;
}
