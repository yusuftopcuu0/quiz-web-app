package com.vector.quiz.common.config;

import com.vector.quiz.modules.quiz.dto.request.CreateAnswerRequest;
import com.vector.quiz.modules.quiz.dto.request.CreateQuestionRequest;
import com.vector.quiz.modules.quiz.dto.request.CreateQuizRequest;
import com.vector.quiz.modules.quiz.entity.QuestionType;
import com.vector.quiz.modules.quiz.service.QuizService;
import com.vector.quiz.modules.user.entity.User;
import com.vector.quiz.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("!test")
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final QuizService quizService;

    @Override
    public void run(String... args) {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("admin", null,
                        List.of(new SimpleGrantedAuthority("ROLE_ADMIN")))
        );


        initializeAdminUser();
        initializeSampleQuiz();

        SecurityContextHolder.clearContext();
    }

    private void initializeAdminUser() {
        if (userRepository.findByUsername("admin").isPresent()) {
            return;
        }

        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@example.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        //TODO: add admin role
//        admin.setRoles(Collections.singleton("ROLE_ADMIN"));

        userRepository.save(admin);
        log.info("Admin user created with username: admin and password: admin123");
    }

    private void initializeSampleQuiz() {
        if (quizService.getAllQuizzes().isEmpty()) {
            CreateAnswerRequest answer1 = new CreateAnswerRequest();
            answer1.setContent("4");
            answer1.setCorrect(true);

            CreateAnswerRequest answer2 = new CreateAnswerRequest();
            answer2.setContent("5");
            answer2.setCorrect(false);

            CreateQuestionRequest question1 = new CreateQuestionRequest();
            question1.setContent("What is 2 + 2?");
            question1.setQuestionType(QuestionType.MULTIPLE_CHOICE);
            question1.setPoints(1);
            question1.setAnswers(Arrays.asList(answer1, answer2));

            CreateQuizRequest quizRequest = new CreateQuizRequest();
            quizRequest.setTitle("Sample Math Quiz");
            quizRequest.setDescription("A simple math quiz to test your knowledge");
            quizRequest.setDurationMinutes(30);
            quizRequest.setIsActive(true);
            quizRequest.setQuestions(Collections.singletonList(question1));

            //TODO: create sample quiz
//            quizService.createQuiz(quizRequest);
            log.info("Sample quiz created");
        }
    }
}
