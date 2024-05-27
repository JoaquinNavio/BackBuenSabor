package com.entidades.buenSabor.domain.dto.Image;
import com.entidades.buenSabor.domain.dto.BaseDto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageDto extends BaseDto {
    private String name;
    private String url;
}
