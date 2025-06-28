package com.vector.quiz.common.service.impl;


import com.vector.quiz.common.service.IAuthenticationService;
import com.vector.quiz.modules.user.entity.User;
import com.vector.quiz.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new RuntimeException("User not authenticated");
        }

        if ("anonymousUser".equals(authentication.getPrincipal())) {
            return null;
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        User userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        return userEntity.getId();
    }


    @Override
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new RuntimeException("RefreshToken not authenticated");
        }
        return (String) authentication.getPrincipal();
    }
}