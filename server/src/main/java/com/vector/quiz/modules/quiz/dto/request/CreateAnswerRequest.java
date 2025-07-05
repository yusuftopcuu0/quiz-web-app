package com.vector.quiz.modules.quiz.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAnswerRequest {
    @NotBlank(message = "Answer content is required")
    private String content;
    
    private boolean isCorrect = false;
}
