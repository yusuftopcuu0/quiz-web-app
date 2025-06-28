package com.vector.quiz.modules.quiz.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CreateQuizRequest {
    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must be less than 255 characters")
    private String title;
    
    private String description;
    
    @PositiveOrZero(message = "Duration must be a positive number or zero")
    private Integer durationMinutes = 30;
    
    private Boolean isActive = true;
    
    @NotNull(message = "Questions cannot be null")
    private List<CreateQuestionRequest> questions;
}
