package com.vector.quiz.common.exception;

import lombok.Getter;

@Getter
public enum MessageType {
    UNAUTHORIZED("401", "Unauthorized"),
    GENERAL_EXCEPTION("1000", "General exception"),
    USERNAME_OR_EMAIL_ALREADY_EXISTS("1001", "Username  already exists"),
    INTERNAL_SERVER_ERROR("1002", "Internal server error"),
    BAD_REQUEST("1003", "Bad request"),
    NO_RECORD_EXIST("1004", "No record exist"),
    TOKEN_IS_EXPIRED("1005", "Token is expired"),
    USERNAME_NOT_FOUND("1006", "Username not found"),
    REFRESH_TOKEN_NOT_FOUND("1008", "Refresh token not found"),
    REFRESH_TOKEN_IS_EXPIRED("1009", "Refresh token is expired"),
    USERNAME_OR_PASSWORD_INVALID("1007", "Username or password invalid"),
    QUIZ_NOT_FOUND("2001", "Quiz not found"),
    QUESTION_NOT_FOUND("2002", "Question not found");

    private final String code;

    private final String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}