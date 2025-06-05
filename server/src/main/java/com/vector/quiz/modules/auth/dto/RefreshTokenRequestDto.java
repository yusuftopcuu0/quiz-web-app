package com.vector.quiz.modules.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenRequestDto {
    @NotEmpty
    private String refreshToken;

    @NotNull
    private Long userId;
}
