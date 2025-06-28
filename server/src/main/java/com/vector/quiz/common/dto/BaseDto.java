package com.vector.quiz.common.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BaseDto {

    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}