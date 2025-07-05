package com.vector.quiz.common.config;

public final class ApiPaths {
    private ApiPaths() { /* instantiation block, prevent new */ }

    public static final String BASE_PATH = "/api";
    public static final String PUBLIC_BASE_PATH = "/public/api";
    public static final String ADMIN_BASE_PATH = "/admin/api";

    public static final class User {
        public static final String ROOT = BASE_PATH + "/user";
        public static final String BY_ID = ROOT + "/{id}";
    }

    public static final class Auth {
        public static final String PUBLIC_ROOT = PUBLIC_BASE_PATH + "/auth";
        public static final String ROOT = BASE_PATH + "/auth";
        public static final String LOGIN = PUBLIC_ROOT + "/login";
        public static final String SIGNUP = PUBLIC_ROOT + "/signup";
        public static final String REFRESH_TOKEN = PUBLIC_ROOT + "/refresh-token";
        public static final String LOGOUT = ROOT + "/logout";
    }
}
