package com.exam.userapi.service.impl;

import com.exam.userapi.dto.UserRequest;
import com.exam.userapi.dto.UserResponse;
import com.exam.userapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log =
            LoggerFactory.getLogger(UserServiceImpl.class);

    // ✅ Spring inyecta AppProperties aquí

    @Override
    public ResponseEntity<UserResponse> register(UserRequest req) {

        log.info("Inicio UserServiceImpl - register");
    
        UserResponse response = UserResponse.builder()
                .message("Usuario creado exitosamente")
                .code(0)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
