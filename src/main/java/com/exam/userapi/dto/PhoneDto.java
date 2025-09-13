package com.exam.userapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PhoneDto {

    @Schema(description = "Número de teléfono", example = "1234567")
    private String number;
    @Schema(description = "Código de ciudad", example = "1")
    private String citycode;
    @Schema(description = "Código de país", example = "57")
    private String countrycode;
}
