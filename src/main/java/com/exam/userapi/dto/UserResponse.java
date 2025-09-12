package com.exam.userapi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserResponse {

    private String message;
    private Integer code;
}
