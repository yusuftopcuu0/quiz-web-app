package com.vector.quiz.modules.auth.controller;

import com.vector.quiz.common.constants.Paths;
import com.vector.quiz.common.controller.ApiResponse;
import com.vector.quiz.common.controller.impl.RestBaseController;
import com.vector.quiz.modules.auth.dto.LoginRequestDto;
import com.vector.quiz.modules.auth.dto.LoginResponseDto;
import com.vector.quiz.modules.auth.dto.RefreshTokenDto;
import com.vector.quiz.modules.auth.dto.SignupRequestDto;
import com.vector.quiz.modules.auth.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Paths.PUBLIC_BASE_PATH + "/auth")
public class AuthControllerImpl extends RestBaseController implements IAuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/login")
    @Override
    public ApiResponse<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ok(authService.login(loginRequestDto));
    }

    @PostMapping("/signup")
    @Override
    public ApiResponse<Void> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return ok(authService.signup(signupRequestDto));
    }

    @PostMapping("/refresh-token")
    @Override
    public ApiResponse<RefreshTokenDto> refreshToken(@Valid @RequestBody String requestRefreshToken) {
        return ok(authService.refreshToken(requestRefreshToken));
    }

    @PostMapping("/logout")
    @Override
    public ApiResponse<Void> logout(@Valid @RequestBody String username) {
        return ok(authService.logout(username));
    }
}
