package com.entidades.buenSabor.domain.dto.Image;
import com.entidades.buenSabor.domain.dto.Base.BaseDto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageDto extends BaseDto {
    private String name;
    private String url;
}
