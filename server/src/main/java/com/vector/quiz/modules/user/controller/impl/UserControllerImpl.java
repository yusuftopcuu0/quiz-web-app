package com.vector.quiz.modules.user.controller.impl;

import com.vector.quiz.common.constants.Paths;
import com.vector.quiz.common.controller.ApiResponse;
import com.vector.quiz.common.controller.impl.RestBaseController;
import com.vector.quiz.modules.user.controller.IUserController;
import com.vector.quiz.modules.user.dto.UserDto;
import com.vector.quiz.modules.user.dto.UserLoginDto;
import com.vector.quiz.modules.user.dto.UserSignupDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Paths.PUBLIC_BASE_PATH + "/user")
public class UserControllerImpl extends RestBaseController implements IUserController {

    @PostMapping("/login")
    @Override
    public ApiResponse<UserDto> login(@Valid @RequestBody UserLoginDto userLoginDto) {
        return ok(null);
    }

    @PostMapping("/signup")
    @Override
    public ApiResponse<UserDto> signup(@Valid @RequestBody UserSignupDto userSignupDto) {
        return ok(null);
    }
}
