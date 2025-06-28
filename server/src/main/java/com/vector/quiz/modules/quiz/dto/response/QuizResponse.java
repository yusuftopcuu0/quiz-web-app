package com.vector.quiz.modules.quiz.dto.response;

import com.vector.quiz.modules.user.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizResponse {
    private Long id;
    private String title;
    private String description;
    private Integer durationMinutes;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserResponse createdBy;
    private List<QuestionResponse> questions;
    
    public Integer getQuestionCount() {
        return questions != null ? questions.size() : 0;
    }
    
    public Integer getTotalPoints() {
        return questions != null ? 
            questions.stream().mapToInt(QuestionResponse::getPoints).sum() : 0;
    }
}
