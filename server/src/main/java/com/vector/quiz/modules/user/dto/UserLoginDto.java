package com.vector.quiz.modules.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {

    @NotNull
    private String login;

    @NotNull
    private String password;
}
