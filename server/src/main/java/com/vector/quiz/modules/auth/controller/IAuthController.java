package com.vector.quiz.modules.auth.controller;

import com.vector.quiz.common.controller.ApiResponse;
import com.vector.quiz.modules.auth.dto.LoginRequestDto;
import com.vector.quiz.modules.auth.dto.LoginResponseDto;
import com.vector.quiz.modules.auth.dto.RefreshTokenDto;
import com.vector.quiz.modules.auth.dto.SignupRequestDto;

public interface IAuthController {

    ApiResponse<LoginResponseDto> login(LoginRequestDto loginRequestDto);

    ApiResponse<Void> signup(SignupRequestDto signupRequestDto);

    ApiResponse<RefreshTokenDto> refreshToken(String requestRefreshToken);

    ApiResponse<Void> logout(String username);
}
