package com.vector.quiz.common.handler;

import com.vector.quiz.common.controller.ApiResponse;
import com.vector.quiz.common.exception.BaseException;
import com.vector.quiz.common.exception.MessageType;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<?>> handleBaseException(BaseException ex) {
        return buildErrorResponse(MessageType.BAD_REQUEST, ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {java.lang.Exception.class})
    public ResponseEntity<ApiResponse<?>> handleException(Exception ex) {
        return buildErrorResponse(
                MessageType.INTERNAL_SERVER_ERROR,
                ex,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResponse<?>> handleExpiredJwtException(ExpiredJwtException ex) {
        return buildErrorResponse(MessageType.TOKEN_IS_EXPIRED, ex, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ApiResponse<?>> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return buildErrorResponse(
                MessageType.INTERNAL_SERVER_ERROR,
                ex,
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<?>> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return buildErrorResponse(MessageType.UNAUTHORIZED, ex, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, List<String>>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        Map<String, List<String>> validationErrors = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            validationErrors
                    .computeIfAbsent(fieldError.getField(), key -> new ArrayList<>())
                    .add(fieldError.getDefaultMessage());
        }
        String errorMessage = "Validation error: " + e.getBody().getDetail();
        return ResponseEntity.badRequest().body(ApiResponse.error(errorMessage, validationErrors));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {

        String message = "JSON parse error: " + e.getMostSpecificCause().getMessage();
        return ResponseEntity.badRequest().body(ApiResponse.error(message));
    }

    private <T extends Exception> ResponseEntity<ApiResponse<?>> buildErrorResponse(
            MessageType type, T ex, HttpStatus status) {
        String msg = type.getMessage() + " : " + type.getCode() + " - " + ex.getMessage();
        return ResponseEntity.status(status).body(ApiResponse.error(msg));
    }

}