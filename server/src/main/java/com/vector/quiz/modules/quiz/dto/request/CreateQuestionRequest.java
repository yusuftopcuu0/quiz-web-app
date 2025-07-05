package com.vector.quiz.modules.quiz.dto.request;

import com.vector.quiz.modules.quiz.entity.QuestionType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CreateQuestionRequest {
    @NotBlank(message = "Content is required")
    private String content;
    
    @NotNull(message = "Question type is required")
    private QuestionType questionType;
    
    @PositiveOrZero(message = "Points must be a positive number or zero")
    private Integer points = 1;
    
    @NotNull(message = "Answers cannot be null")
    @Size(min = 1, message = "At least one answer is required")
    private List<@Valid CreateAnswerRequest> answers;
}
