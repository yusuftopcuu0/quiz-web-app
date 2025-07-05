package com.vector.quiz.modules.quiz.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponse {
    private Long id;
    private String content;
    private Boolean isCorrect;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
