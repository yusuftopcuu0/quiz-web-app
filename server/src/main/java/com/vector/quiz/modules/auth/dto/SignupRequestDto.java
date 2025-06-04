package com.vector.quiz.modules.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @NotNull(message = "Username cannot be null")
    @Size(min = 3, message = "Username should be at least 3 characters")
    private String username;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 6, message = "Password should be at least 6 characters")
    private String password;
}
