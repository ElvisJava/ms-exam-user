package com.exam.userapi.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserResponse {

    private String id;
    private String name;
    private OffsetDateTime created;
    private OffsetDateTime modified;
    private OffsetDateTime lastLogin;
    private String token;
    private boolean isActive;
}
