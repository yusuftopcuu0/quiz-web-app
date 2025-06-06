package com.vector.quiz.modules.auth.dto;

import com.vector.quiz.common.dto.BaseDto;
import com.vector.quiz.common.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto extends BaseDto {

    private Long id;

    private String username;

    private String email;

    private Role role;

    private String accessToken;

    private String refreshToken;
}
