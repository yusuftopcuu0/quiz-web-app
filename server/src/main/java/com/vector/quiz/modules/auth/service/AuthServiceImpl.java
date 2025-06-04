package com.vector.quiz.modules.auth.service;

import com.vector.quiz.common.enums.Role;
import com.vector.quiz.common.exception.BaseException;
import com.vector.quiz.common.exception.ErrorMessage;
import com.vector.quiz.common.exception.MessageType;
import com.vector.quiz.common.service.impl.AuthenticationServiceImpl;
import com.vector.quiz.common.utils.JwtUtils;
import com.vector.quiz.modules.auth.dto.LoginRequestDto;
import com.vector.quiz.modules.auth.dto.LoginResponseDto;
import com.vector.quiz.modules.auth.dto.RefreshTokenDto;
import com.vector.quiz.modules.auth.dto.SignupRequestDto;
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
    public RefreshTokenDto refreshToken(String requestRefreshToken) {
        Optional<RefreshToken> refreshTokenOpt = refreshTokenRepository.findByToken(requestRefreshToken);
        if (refreshTokenOpt.isEmpty()) {
            throw new RuntimeException("Refresh Token bulunamadı");
        }
        RefreshToken refreshToken = refreshTokenOpt.get();

        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh Token süresi dolmuş");
        }
        Long userId = authenticationServiceImpl.getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, null)));
        String username = jwtUtils.getUsernameFromJwtToken(requestRefreshToken);
        String newAccessToken = jwtUtils.generateAccessToken(user);

        // Opsiyonel: rotate etmek için eskiyi sil, yenisini veritabanına kaydet
        refreshTokenRepository.delete(refreshToken);
        String newRefreshString = jwtUtils.generateRefreshToken(username);
        RefreshToken newRefreshTokenEntity = new RefreshToken();
        newRefreshTokenEntity.setUser(refreshToken.getUser());
        newRefreshTokenEntity.setToken(newRefreshString);
        newRefreshTokenEntity.setExpiryDate(Instant.now().plusMillis(jwtUtils.getJwtRefreshExpirationMs()));
        refreshTokenRepository.save(newRefreshTokenEntity);

        return modelMapper.map(newRefreshTokenEntity, RefreshTokenDto.class);
    }

    @Transactional
    public Void logout(String username) {
        Long userId = authenticationServiceImpl.getCurrentUserId();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        refreshTokenRepository.deleteByUser(user);
        return null;
    }

}
