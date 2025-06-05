package com.vector.quiz.common.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

    private boolean status;

    private T payload;

    private String errorMessage;

    public static <T> ApiResponse<T> success(T payload) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(true);
        apiResponse.setErrorMessage(null);
        apiResponse.setPayload(payload);
        return apiResponse;
    }

    public static <T> ApiResponse<T> error(String errorMessage) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(false);
        apiResponse.setErrorMessage(errorMessage);
        apiResponse.setPayload(null);
        return apiResponse;
    }

    public static <T> ApiResponse<T> error(String errorMessage, T payload) {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(false);
        apiResponse.setErrorMessage(errorMessage);
        apiResponse.setPayload(payload);
        return apiResponse;
    }
}

