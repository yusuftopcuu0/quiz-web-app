package com.vector.quiz.modules.quiz.repository;

import com.vector.quiz.modules.quiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    
    @Query("SELECT q FROM Quiz q WHERE q.isActive = true")
    List<Quiz> findAllActive();
    
    @Query("SELECT q FROM Quiz q WHERE q.id = :id AND q.isActive = true")
    Optional<Quiz> findActiveById(@Param("id") Long id);
    
    @Query("SELECT q FROM Quiz q JOIN FETCH q.questions qu LEFT JOIN FETCH qu.answers WHERE q.id = :id")
    Optional<Quiz> findByIdWithQuestionsAndAnswers(@Param("id") Long id);
}
