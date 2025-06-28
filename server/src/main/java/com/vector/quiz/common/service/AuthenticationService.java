package com.vector.quiz.common.service;

import com.vector.quiz.modules.user.entity.User;

public interface AuthenticationService {
    User getCurrentUser();
    Long getCurrentUserId();
}
