package com.vector.quiz.modules.quiz.controller;

import com.vector.quiz.common.controller.ApiResponse;
import com.vector.quiz.modules.quiz.dto.request.CreateQuizRequest;
import com.vector.quiz.modules.quiz.dto.request.UpdateQuizRequest;
import com.vector.quiz.modules.quiz.dto.response.QuizResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Quiz Management", description = "APIs for managing quizzes")
@SecurityRequirement(name = "bearerAuth")
public interface IQuizController {

    @GetMapping
    @Operation(summary = "Get all active quizzes")
    ApiResponse<List<QuizResponse>> getAllQuizzes();

    @GetMapping("/{id}")
    @Operation(summary = "Get a quiz by ID")
    ApiResponse<QuizResponse> getQuizById(@PathVariable Long id);

    @GetMapping("/{id}/with-questions")
    @Operation(summary = "Get a quiz with all questions and answers by ID")
    ApiResponse<QuizResponse> getQuizWithQuestionsAndAnswers(@PathVariable Long id);

    @PostMapping
    @Operation(summary = "Create a new quiz (Admin only)")
    ApiResponse<QuizResponse> createQuiz(@Valid @RequestBody CreateQuizRequest request);

    @PutMapping("/{id}")
    @Operation(summary = "Update a quiz (Admin only)")
    ApiResponse<QuizResponse> updateQuiz(
            @PathVariable Long id,
            @Valid @RequestBody UpdateQuizRequest request);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a quiz (Admin only)")
    ApiResponse<Void> deleteQuiz(@PathVariable Long id);
}
