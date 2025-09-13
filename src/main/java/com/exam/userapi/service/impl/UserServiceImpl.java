package com.exam.userapi.service.impl;

import com.exam.userapi.dto.UserRequest;
import com.exam.userapi.dto.UserResponse;
import com.exam.userapi.entity.PhoneEntity;
import com.exam.userapi.entity.UserEntity;
import com.exam.userapi.exception.EmailAlreadyRegisteredException;
import com.exam.userapi.repository.UserRepository;
import com.exam.userapi.security.JwtUtil;
import com.exam.userapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log =
            LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public ResponseEntity<UserResponse> register(UserRequest req) {

        log.info("Inicio UserServiceImpl - register");

        userRepository.findByEmail(req.getEmail()).ifPresent(u -> { throw
                new EmailAlreadyRegisteredException(); });
        var now = OffsetDateTime.now();
        String token = jwtUtil.generateToken(req.getEmail(), UUID.randomUUID().toString());
        log.info("token: " + token);
        userRepository.save(UserEntity.builder()
                .id(UUID.randomUUID().toString())
                .name(req.getName())
                .email(req.getEmail())
                .password(req.getPassword())
                .phones(req.getPhones().stream().map(p -> PhoneEntity.builder()
                        .number(p.getNumber())
                        .citycode(p.getCitycode())
                        .countrycode(p.getCountrycode())
                        .build()).toList())
                .isActive(true)
                .created(now)
                .modified(now)
                .lastLogin(now)
                .token(token)
                .build());

        UserResponse response = UserResponse.builder()
                .message("Usuario creado exitosamente")
                .code(0)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
