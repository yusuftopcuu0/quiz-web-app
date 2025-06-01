package com.vector.quiz.common.config;

import com.vector.quiz.common.enums.UserRole;
import com.vector.quiz.common.exception.BaseException;
import com.vector.quiz.common.exception.ErrorMessage;
import com.vector.quiz.common.exception.MessageType;
import com.vector.quiz.common.jwt.JWTAuthenticationFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private static final String[] SWAGGER_PATHS = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml"
    };
    private static final String ADMIN_PATH = "/admin/**";
    private static final String PUBLIC_PATHS = "/public/**";

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;


//    @Autowired
//    private RefreshTokenRepository refreshTokenRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers(SWAGGER_PATHS).permitAll()
                        .requestMatchers(PUBLIC_PATHS).permitAll()
                        .requestMatchers(ADMIN_PATH).hasRole(UserRole.ADMIN.name())
                        .anyRequest().authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            try {
                                handleLogout(request, response);
                            } catch (BaseException e) {
                                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                                response.setContentType("application/json");
                                response.getWriter().write("{ \"error\": \"" + e.getMessage() + "\" }");
                            }
                        })
                        .clearAuthentication(true)
                        .invalidateHttpSession(true));

        return http.build();
    }

    public void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new BaseException(
                    new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, "Token not found"));
        }

        String jwt = authHeader.substring(7);
        //TODO change to refresh token
//        RefreshToken storedToken = refreshTokenRepository.findByRefreshToken(jwt).orElse(null);

//        if (storedToken == null) {
//            throw new BaseException(
//                    new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, "Token not found"));
//        }
//
//        refreshTokenRepository.delete(storedToken);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().write("{ \"message\": \"Logout successful\" }");
    }

}