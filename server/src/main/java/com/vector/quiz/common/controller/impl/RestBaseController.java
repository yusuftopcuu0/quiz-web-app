package com.vector.quiz.common.controller.impl;

import com.vector.quiz.common.controller.ApiResponse;

public class RestBaseController {

    public <T> ApiResponse<T> ok(T payload) {
        return ApiResponse.success(payload);
    }

    public <T> ApiResponse<T> error(String errorMessage) {
        return ApiResponse.error(errorMessage);
    }
}