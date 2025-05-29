package com.vector.quiz.modules.user.dto;

import com.vector.quiz.common.dto.BaseDto;
import com.vector.quiz.common.enums.UserRole;
import jakarta.validation.constraints.NotNull;

public class UserDto extends BaseDto {

    @NotNull
    private String username;

    @NotNull
    private String email;

    private UserRole userRole;
}
