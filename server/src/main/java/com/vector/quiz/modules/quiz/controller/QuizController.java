package com.vector.quiz.modules.quiz.controller;

import com.vector.quiz.common.controller.ApiResponse;
import com.vector.quiz.common.controller.impl.RestBaseController;
import com.vector.quiz.modules.quiz.dto.request.CreateQuizRequest;
import com.vector.quiz.modules.quiz.dto.request.UpdateQuizRequest;
import com.vector.quiz.modules.quiz.dto.response.QuizResponse;
import com.vector.quiz.modules.quiz.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
@Tag(name = "Quiz Management", description = "APIs for managing quizzes")
@SecurityRequirement(name = "bearerAuth")
public class QuizController extends RestBaseController implements IQuizController {

    private final QuizService quizService;

    @GetMapping
    @Operation(summary = "Get all active quizzes")
    @Override
    public ApiResponse<List<QuizResponse>> getAllQuizzes() {
        return ok(quizService.getAllQuizzes());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a quiz by ID")
    @Override
    public ApiResponse<QuizResponse> getQuizById(@PathVariable Long id) {
        return ok(quizService.getQuizById(id));
    }

    @GetMapping("/{id}/detailed")
    @Operation(summary = "Get a quiz with all questions and answers")
    @Override
    public ApiResponse<QuizResponse> getQuizWithQuestionsAndAnswers(@PathVariable Long id) {
        return ok(quizService.getQuizWithQuestionsAndAnswers(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Create a new quiz (Admin only)")
    @Override
    public ApiResponse<QuizResponse> createQuiz(@Valid @RequestBody CreateQuizRequest request) {
        return ok(quizService.createQuiz(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Update a quiz (Admin only)")
    @Override
    public ApiResponse<QuizResponse> updateQuiz(
            @PathVariable Long id,
            @Valid @RequestBody UpdateQuizRequest request) {
        return ok(quizService.updateQuiz(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Delete a quiz (Soft delete, Admin only)")
    @Override
    public ApiResponse<Void> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ok(null);
    }
}
