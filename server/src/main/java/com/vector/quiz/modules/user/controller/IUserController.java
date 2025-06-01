package com.vector.quiz.modules.user.controller;

import com.vector.quiz.common.controller.ApiResponse;
import com.vector.quiz.modules.user.dto.UserDto;
import com.vector.quiz.modules.user.dto.UserLoginDto;
import com.vector.quiz.modules.user.dto.UserSignupDto;

public interface IUserController {

    ApiResponse<UserDto> login(UserLoginDto userLoginDto);

    ApiResponse<UserDto> signup(UserSignupDto userSignupDto);
}
