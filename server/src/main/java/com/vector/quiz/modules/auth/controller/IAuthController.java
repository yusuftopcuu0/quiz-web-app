package com.vector.quiz.modules.auth.controller;

import com.vector.quiz.common.controller.ApiResponse;
import com.vector.quiz.modules.auth.dto.*;

public interface IAuthController {

    ApiResponse<LoginResponseDto> login(LoginRequestDto loginRequestDto);

    ApiResponse<Void> signup(SignupRequestDto signupRequestDto);

    ApiResponse<RefreshTokenDto> refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);

    ApiResponse<Void> logout();
}
