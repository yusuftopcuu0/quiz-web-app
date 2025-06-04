package com.vector.quiz.modules.auth.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

    @Nullable
    private String username;

    @Nullable
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Password be null")
    @Size(min = 6, message = "Password should be at least 6 characters")
    private String password;
}
