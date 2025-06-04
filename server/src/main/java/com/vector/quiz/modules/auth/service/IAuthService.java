package com.vector.quiz.modules.auth.service;

import com.vector.quiz.modules.auth.dto.LoginRequestDto;
import com.vector.quiz.modules.auth.dto.LoginResponseDto;
import com.vector.quiz.modules.auth.dto.RefreshTokenDto;
import com.vector.quiz.modules.auth.dto.SignupRequestDto;

public interface IAuthService {

    LoginResponseDto login(LoginRequestDto loginRequestDto);

    Void signup(SignupRequestDto signupRequestDto);

    RefreshTokenDto refreshToken(String requestRefreshToken);

    Void logout(String username);
}
