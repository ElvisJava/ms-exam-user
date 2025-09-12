package com.exam.userapi.service;

import com.exam.userapi.dto.UserRequest;
import com.exam.userapi.dto.UserResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserResponse> register(UserRequest request);

}
