package com.vector.quiz.modules.auth.service;

import com.vector.quiz.modules.auth.dto.*;

public interface IAuthService {

    LoginResponseDto login(LoginRequestDto loginRequestDto);

    Void signup(SignupRequestDto signupRequestDto);

    RefreshTokenDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);

    Void logout();
}
