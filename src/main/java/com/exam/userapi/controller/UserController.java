package com.exam.userapi.controller;

import com.exam.userapi.dto.UserRequest;
import com.exam.userapi.dto.UserResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/exam", produces =
        MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private static final Logger log =
            LoggerFactory.getLogger(UserController.class);

    @PostMapping(path = "/register/user", consumes =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> register(@Valid @RequestBody
                                                 UserRequest request) {
        log.info("Solicitud de registro para correo={}", request.getEmail());
        UserResponse response = UserResponse.builder()
                .message("Usuario creado exitosamente")
                .code(0)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
