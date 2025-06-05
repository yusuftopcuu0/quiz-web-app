package com.vector.quiz.modules.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenDto {
    private String accessToken;
    private String refreshToken;
}
