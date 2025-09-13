package com.exam.userapi.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserRequest {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;


    @Email(message = "Correo con formato invalido")
    @NotBlank(message = "El correo no debe ser vacio")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "El correo debe tener un formato válido, ej: aaaaa@zzzz.com"
    )
    private String email;


    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$",
            message = "La contraseña debe contener mayúscula, minúscula y un número"
    )
    private String password;
    @NotEmpty
    private List<PhoneDto> phones;
}
