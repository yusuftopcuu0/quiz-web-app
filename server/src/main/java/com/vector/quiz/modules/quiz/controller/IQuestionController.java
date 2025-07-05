package com.vector.quiz.modules.quiz.controller;

import com.vector.quiz.common.controller.ApiResponse;
import com.vector.quiz.modules.quiz.dto.request.CreateQuestionRequest;
import com.vector.quiz.modules.quiz.dto.response.QuestionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Question Management", description = "APIs for managing questions in quizzes")
@SecurityRequirement(name = "bearerAuth")
public interface IQuestionController {

    @GetMapping
    @Operation(summary = "Get all questions for a quiz")
    ApiResponse<List<QuestionResponse>> getQuestionsByQuizId(@PathVariable Long quizId);

    @GetMapping("/{questionId}")
    @Operation(summary = "Get a specific question from a quiz")
    ApiResponse<QuestionResponse> getQuestionById(
            @PathVariable Long quizId,
            @PathVariable Long questionId);

    @PostMapping
    @Operation(summary = "Add a new question to a quiz (Admin only)")
    ApiResponse<QuestionResponse> addQuestionToQuiz(
            @PathVariable Long quizId,
            @Valid @RequestBody CreateQuestionRequest request);

    @DeleteMapping("/{questionId}")
    @Operation(summary = "Remove a question from a quiz (Admin only)")
    ApiResponse<Void> removeQuestionFromQuiz(
            @PathVariable Long quizId,
            @PathVariable Long questionId);
}
