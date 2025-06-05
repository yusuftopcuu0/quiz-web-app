package com.vector.quiz.modules.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupDto {

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;
}
