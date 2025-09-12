package com.exam.userapi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PhoneDto {

    private String number;
    private String citycode;
    private String countrycode;
}
