package com.vector.quiz.modules.auth.service;

import com.vector.quiz.common.enums.Role;
import com.vector.quiz.common.exception.BaseException;
import com.vector.quiz.common.exception.ErrorMessage;
import com.vector.quiz.common.exception.MessageType;
import com.vector.quiz.common.service.impl.AuthenticationServiceImpl;
import com.vector.quiz.common.utils.JwtUtils;
import com.vector.quiz.modules.auth.dto.*;
import com.vector.quiz.modules.auth.entity.RefreshToken;
import com.vector.quiz.modules.auth.repository.RefreshTokenRepository;
import com.vector.quiz.modules.user.entity.User;
import com.vector.quiz.modules.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuthenticationServiceImpl authenticationServiceImpl;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );
        User user = userRepository.findByUsernameOrEmail(loginRequestDto.getUsername(), loginRequestDto.getUsername())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, loginRequestDto.getUsername())));

        String accessToken = jwtUtils.generateAccessToken(user);
        String refreshTokenString = jwtUtils.generateRefreshToken(loginRequestDto.getUsername());

        refreshTokenRepository.findByUser(user).ifPresent(existing -> {
            refreshTokenRepository.delete(existing);
        });


        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userRepository.findByUsername(loginRequestDto.getUsername()).orElseThrow());
        refreshToken.setToken(refreshTokenString);
        refreshToken.setExpiryDate(Instant.now().plusMillis(jwtUtils.getJwtRefreshExpirationMs()));
        refreshTokenRepository.save(refreshToken);

        LoginResponseDto dto = modelMapper.map(user, LoginResponseDto.class);
        dto.setAccessToken(accessToken);
        dto.setRefreshToken(refreshTokenString);
        return dto;
    }

    @Override
    public Void signup(SignupRequestDto signupRequestDto) {
        if (userRepository.existsByUsername(signupRequestDto.getUsername()) || userRepository.existsByEmail(signupRequestDto.getEmail())) {
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_EMAIL_ALREADY_EXISTS, null));
        }
        User user = new User();
        user.setUsername(signupRequestDto.getUsername());
        user.setEmail(signupRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        return null;
    }


    @Transactional
    public RefreshTokenDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        Optional<RefreshToken> refreshTokenOpt = refreshTokenRepository.findByToken(refreshTokenRequestDto.getRefreshToken());
        if (refreshTokenOpt.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, null));
        }
        RefreshToken refreshToken = refreshTokenOpt.get();

        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, null));
        }
        User user = userRepository.findById(refreshTokenRequestDto.getUserId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, null)));
        String username = jwtUtils.getUsernameFromJwtToken(refreshTokenRequestDto.getRefreshToken());
        String newAccessToken = jwtUtils.generateAccessToken(user);

        String newRefreshString = jwtUtils.generateRefreshToken(username);
        refreshToken.setToken(newRefreshString);
        refreshToken.setExpiryDate(
                Instant.now().plusMillis(jwtUtils.getJwtRefreshExpirationMs()));
        refreshTokenRepository.save(refreshToken);

        RefreshTokenDto response = new RefreshTokenDto();
        response.setAccessToken(newAccessToken);
        response.setRefreshToken(newRefreshString);
        return response;
    }

    @Transactional
    public Void logout() {
        Long userId = authenticationServiceImpl.getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Kullanıcı bulunamadı")));
        refreshTokenRepository.deleteByUser(user);
        return null;
    }

}
