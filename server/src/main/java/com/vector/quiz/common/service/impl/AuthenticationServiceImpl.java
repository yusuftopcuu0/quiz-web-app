package com.vector.quiz.common.service.impl;

import com.vector.quiz.common.service.AuthenticationService;
import com.vector.quiz.modules.user.entity.User;
import com.vector.quiz.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new RuntimeException("User not authenticated");
        }

        if ("anonymousUser".equals(authentication.getPrincipal())) {
            throw new RuntimeException("No authenticated user");
        }

        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

//    @Override
//    public Long getCurrentUserId() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication.getPrincipal() == null) {
//            throw new RuntimeException("User not authenticated");
//        }
//
//        if ("anonymousUser".equals(authentication.getPrincipal())) {
//            return null;
//        }
//
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String username = userDetails.getUsername();
//
//        User userEntity = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
//        return userEntity.getId();
//    }

    @Override
    public Long getCurrentUserId() {
        return getCurrentUser().getId();
    }
}