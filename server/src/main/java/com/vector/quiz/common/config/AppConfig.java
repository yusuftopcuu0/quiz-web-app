package com.vector.quiz.common.config;


import com.vector.quiz.common.exception.BaseException;
import com.vector.quiz.common.exception.ErrorMessage;
import com.vector.quiz.common.exception.MessageType;
import com.vector.quiz.modules.user.entity.User;
import com.vector.quiz.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class AppConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.USERNAME_NOT_FOUND, username)));

            // Kullanıcının rolünü authorities’a çeviriyoruz. Eğer tek rol tutuyorsanız:
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
            List<GrantedAuthority> authorities = List.of(authority);

            // Spring’in kendi UserDetails implementasyonu:
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    authorities
            );
        };
//        return new UserDetailsService() {
//
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                Optional<User> optional = userRepository.findByUsername(username);
//                if (optional.isEmpty()) {
//                    throw new BaseException(new ErrorMessage(MessageType.USERNAME_NOT_FOUND, username));
//                }
//                return (UserDetails) optional.get();
//
//            }
//
//        };
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}