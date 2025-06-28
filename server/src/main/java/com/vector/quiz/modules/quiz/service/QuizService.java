package com.vector.quiz.modules.quiz.service;

import com.vector.quiz.modules.quiz.dto.request.CreateQuizRequest;
import com.vector.quiz.modules.quiz.dto.request.UpdateQuizRequest;
import com.vector.quiz.modules.quiz.dto.response.QuizResponse;

import java.util.List;

public interface QuizService {
    List<QuizResponse> getAllQuizzes();
    QuizResponse getQuizById(Long id);
    QuizResponse createQuiz(CreateQuizRequest request);
    QuizResponse updateQuiz(Long id, UpdateQuizRequest request);
    void deleteQuiz(Long id);
    QuizResponse getQuizWithQuestionsAndAnswers(Long id);
}
