package com.exam.userapi.service;

import com.exam.userapi.dto.PhoneDto;
import com.exam.userapi.dto.UserRequest;
import com.exam.userapi.dto.UserResponse;
import com.exam.userapi.entity.PhoneEntity;
import com.exam.userapi.entity.UserEntity;
import com.exam.userapi.exception.EmailAlreadyRegisteredException;
import com.exam.userapi.repository.UserRepository;
import com.exam.userapi.security.JwtUtil;
import com.exam.userapi.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_success() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setName("Juan Rodriguez");
        request.setEmail("juan@rodriguez.org");
        request.setPassword("Abc12345");
        request.setPhones(List.of(
                new PhoneDto("1234567", "1", "57")
        ));


        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.empty());

        when(jwtUtil.generateToken(eq(request.getEmail()), anyString()))
                .thenReturn("fake-jwt-token");

        // simulamos que el repositorio devuelve un UserEntity guardado
        UserEntity savedUser = UserEntity.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .phones(List.of(
                        PhoneEntity.builder()
                                .number("1234567")
                                .citycode("1")
                                .countrycode("57")
                                .build()
                ))
                .isActive(true)
                .created(OffsetDateTime.now())
                .modified(OffsetDateTime.now())
                .lastLogin(OffsetDateTime.now())
                .token("fake-jwt-token")
                .build();

        when(userRepository.save(any(UserEntity.class)))
                .thenReturn(savedUser);

        // WHEN
        ResponseEntity<UserResponse> response = userService.register(request);

        // THEN
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Juan Rodriguez", response.getBody().getName());
        assertEquals("fake-jwt-token", response.getBody().getToken());
        assertTrue(response.getBody().isActive());

        verify(userRepository, times(1)).findByEmail(request.getEmail());
        verify(userRepository, times(1)).save(any(UserEntity.class));
        verify(jwtUtil, times(1)).generateToken(eq(request.getEmail()), anyString());
    }

    @Test
    void register_emailAlreadyExists() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setEmail("juan@rodriguez.org");

        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(new UserEntity()));

        // WHEN + THEN
        assertThrows(EmailAlreadyRegisteredException.class,
                () -> userService.register(request));

        verify(userRepository, times(1)).findByEmail(request.getEmail());
        verify(userRepository, never()).save(any());
        verify(jwtUtil, never()).generateToken(anyString(), anyString());
    }
}
