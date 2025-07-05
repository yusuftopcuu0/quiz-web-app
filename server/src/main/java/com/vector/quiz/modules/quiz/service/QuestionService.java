package com.vector.quiz.modules.quiz.service;

import com.vector.quiz.modules.quiz.dto.request.CreateQuestionRequest;
import com.vector.quiz.modules.quiz.dto.response.QuestionResponse;

import java.util.List;

public interface QuestionService {
    List<QuestionResponse> getQuestionsByQuizId(Long quizId);
    QuestionResponse getQuestionById(Long quizId, Long questionId);
    QuestionResponse addQuestionToQuiz(Long quizId, CreateQuestionRequest request);
    void removeQuestionFromQuiz(Long quizId, Long questionId);
}
