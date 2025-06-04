package com.vector.quiz.modules.user.entity;

import com.vector.quiz.common.entity.BaseEntity;
import com.vector.quiz.common.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
