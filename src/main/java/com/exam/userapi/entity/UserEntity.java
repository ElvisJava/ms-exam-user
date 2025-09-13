package com.exam.userapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(name = "id", nullable = false, length = 36)
    private String id;
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<PhoneEntity> phones = new ArrayList<>();

    private OffsetDateTime created;
    private OffsetDateTime modified;
    private OffsetDateTime lastLogin;

    @Column(length = 512)
    private String token;
    private boolean isActive;
}
