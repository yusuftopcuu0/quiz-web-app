package com.vector.quiz.modules.quiz.repository;

import com.vector.quiz.modules.quiz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    
    List<Question> findByQuizId(Long quizId);
    
    @Query("SELECT q FROM Question q LEFT JOIN FETCH q.answers WHERE q.quiz.id = :quizId")
    List<Question> findByQuizIdWithAnswers(@Param("quizId") Long quizId);
    
    @Query("SELECT q FROM Question q LEFT JOIN FETCH q.answers WHERE q.id = :id")
    Optional<Question> findByIdWithAnswers(@Param("id") Long id);
    
    boolean existsByIdAndQuizId(Long id, Long quizId);
}
