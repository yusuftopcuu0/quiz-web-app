package com.vector.quiz.modules.quiz.controller;

import com.vector.quiz.common.controller.ApiResponse;
import com.vector.quiz.common.controller.impl.RestBaseController;
import com.vector.quiz.modules.quiz.dto.request.CreateQuestionRequest;
import com.vector.quiz.modules.quiz.dto.response.QuestionResponse;
import com.vector.quiz.modules.quiz.service.QuestionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes/{quizId}/questions")
@RequiredArgsConstructor
@Tag(name = "Question Management")
public class QuestionController extends RestBaseController implements IQuestionController {

    private final QuestionService questionService;

    @Override
    public ApiResponse<List<QuestionResponse>> getQuestionsByQuizId(@PathVariable Long quizId) {
        return ok(questionService.getQuestionsByQuizId(quizId));
    }

    @Override
    public ApiResponse<QuestionResponse> getQuestionById(
            @PathVariable Long quizId,
            @PathVariable Long questionId) {
        return ok(questionService.getQuestionById(quizId, questionId));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse<QuestionResponse> addQuestionToQuiz(
            @PathVariable Long quizId,
            @Valid @RequestBody CreateQuestionRequest request) {
        return ok(questionService.addQuestionToQuiz(quizId, request));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ApiResponse<Void> removeQuestionFromQuiz(
            @PathVariable Long quizId,
            @PathVariable Long questionId) {
        questionService.removeQuestionFromQuiz(quizId, questionId);
        return ok(null);
    }
}
