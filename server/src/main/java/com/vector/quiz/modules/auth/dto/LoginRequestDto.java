package com.vector.quiz.modules.auth.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

    @NotNull(message = "Username cannot be null")
    @Size(min = 3, message = "Username should be at least 3 characters")
    private String login;

    @NotNull(message = "Password be null")
    @Size(min = 8, message = "Password should be at least 6 characters")
    private String password;
}
