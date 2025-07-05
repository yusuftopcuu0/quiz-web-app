package com.vector.quiz.modules.quiz.dto.response;

import com.vector.quiz.modules.quiz.entity.QuestionType;
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
public class QuestionResponse {
    private Long id;
    private String content;
    private QuestionType questionType;
    private Integer points;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<AnswerResponse> answers;
}
