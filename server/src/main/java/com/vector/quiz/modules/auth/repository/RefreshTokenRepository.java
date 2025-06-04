package com.vector.quiz.modules.auth.repository;

import com.vector.quiz.modules.auth.entity.RefreshToken;
import com.vector.quiz.modules.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    int deleteByUser(User user);

}
