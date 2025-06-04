package com.vector.quiz.common.handler;

import com.vector.quiz.common.controller.ApiResponse;
import com.vector.quiz.common.exception.BaseException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.nio.file.AccessDeniedException;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<?>> handleBaseException(BaseException ex) {
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(value = {java.lang.Exception.class})
    public ResponseEntity<ApiResponse<?>> handleException(Exception ex) {
        // Genel hata için de 400 ile dönebilir veya isterseniz INTERNAL_SERVER_ERROR ayarlayabilirsiniz
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(ex.getMessage().toString()));
    }


    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<ApiResponse<?>> handleExpiredJwtException(ExpiredJwtException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ApiResponse<?>> handleJwtRuntimeException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<ApiResponse<?>> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResponse<Map<String, List<String>>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e, WebRequest request) {

        Map<String, List<String>> map = new HashMap<>();
        for (ObjectError objError : e.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError) objError).getField();
            if (map.containsKey(fieldName)) {
                map.put(fieldName, addValue(map.get(fieldName), objError.getDefaultMessage()));
            } else {
                map.put(fieldName, addValue(new ArrayList<>(), objError.getDefaultMessage()));
            }
        }

        return ResponseEntity.badRequest().body(ApiResponse.error(map));
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<ApiResponse<?>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e,
                                                                                WebRequest request) {
        String message = e.getMessage();

        return ResponseEntity.badRequest().body(ApiResponse.error(message));
    }

    private List<String> addValue(List<String> list, String value) {
        list.add(value);
        return list;
    }

    private String getHostName() {
        try {
            return Inet4Address.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "";
    }

    public <E> ApiError<E> createApiError(E message, WebRequest request) {
        ApiError<E> apiError = new ApiError<>();
        apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
        Exception<E> exception = new Exception<>();
        exception.setPath(request.getDescription(false));
        exception.setMessage(message);
        exception.setCreatedAt(new Date());
        exception.setHostName(getHostName());

        apiError.setException(exception);

        return apiError;
    }
}