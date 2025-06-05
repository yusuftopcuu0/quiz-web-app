package com.vector.quiz.modules.auth.controller;

import com.vector.quiz.common.config.ApiPaths;
import com.vector.quiz.common.controller.ApiResponse;
import com.vector.quiz.common.controller.impl.RestBaseController;
import com.vector.quiz.modules.auth.dto.*;
import com.vector.quiz.modules.auth.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl extends RestBaseController implements IAuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping(ApiPaths.Auth.LOGIN)
    @Override
    public ApiResponse<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ok(authService.login(loginRequestDto));
    }

    @PostMapping(ApiPaths.Auth.SIGNUP)
    @Override
    public ApiResponse<Void> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return ok(authService.signup(signupRequestDto));
    }

    @PostMapping(ApiPaths.Auth.REFRESH_TOKEN)
    @Override
    public ApiResponse<RefreshTokenDto> refreshToken(@Valid @RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        return ok(authService.refreshToken(refreshTokenRequestDto));
    }

    @PostMapping(ApiPaths.Auth.LOGOUT)
    @Override
    public ApiResponse<Void> logout() {
        return ok(authService.logout());
    }
}
