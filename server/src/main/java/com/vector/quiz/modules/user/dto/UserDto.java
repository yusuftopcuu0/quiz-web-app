package com.vector.quiz.modules.user.dto;

import com.vector.quiz.common.dto.BaseDto;
import com.vector.quiz.common.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends BaseDto {

    @NotNull
    private String username;

    @NotNull
    private String email;

    private Role role;
}
