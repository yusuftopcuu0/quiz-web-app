package com.vector.quiz.modules.quiz.service.impl;

import com.vector.quiz.common.service.AuthenticationService;
import com.vector.quiz.modules.quiz.dto.request.CreateAnswerRequest;
import com.vector.quiz.modules.quiz.dto.request.CreateQuestionRequest;
import com.vector.quiz.modules.quiz.dto.request.CreateQuizRequest;
import com.vector.quiz.modules.quiz.dto.request.UpdateQuizRequest;
import com.vector.quiz.modules.quiz.dto.response.QuizResponse;
import com.vector.quiz.modules.quiz.entity.Answer;
import com.vector.quiz.modules.quiz.entity.Question;
import com.vector.quiz.modules.quiz.entity.Quiz;
import com.vector.quiz.common.exception.BaseException;
import com.vector.quiz.common.exception.ErrorMessage;
import com.vector.quiz.common.exception.MessageType;
import com.vector.quiz.modules.quiz.repository.QuizRepository;
import com.vector.quiz.modules.quiz.service.QuizService;
import com.vector.quiz.modules.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final ModelMapper modelMapper;
    private final AuthenticationService authenticationService;

    @Override
    @Transactional(readOnly = true)
    public List<QuizResponse> getAllQuizzes() {
        return quizRepository.findAllActive().stream()
                .map(quiz -> modelMapper.map(quiz, QuizResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public QuizResponse getQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.QUIZ_NOT_FOUND, id.toString())));
        return modelMapper.map(quiz, QuizResponse.class);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public QuizResponse createQuiz(CreateQuizRequest request) {
        User currentUser = authenticationService.getCurrentUser();

        Quiz quiz = new Quiz();
        quiz.setTitle(request.getTitle());
        quiz.setDescription(request.getDescription());
        quiz.setDurationMinutes(request.getDurationMinutes());
        quiz.setIsActive(request.getIsActive());
        quiz.setCreatedBy(currentUser);

        // Add questions
        if (request.getQuestions() != null) {
            for (CreateQuestionRequest questionRequest : request.getQuestions()) {
                Question question = new Question();
                question.setContent(questionRequest.getContent());
                question.setQuestionType(questionRequest.getQuestionType());
                question.setPoints(questionRequest.getPoints());

                // Add answers
                if (questionRequest.getAnswers() != null) {
                    for (CreateAnswerRequest answerRequest : questionRequest.getAnswers()) {
                        Answer answer = new Answer();
                        answer.setContent(answerRequest.getContent());
                        answer.setIsCorrect(answerRequest.isCorrect());
                        question.addAnswer(answer);
                    }
                }

                quiz.addQuestion(question);
            }
        }

        Quiz savedQuiz = quizRepository.save(quiz);
        return modelMapper.map(savedQuiz, QuizResponse.class);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public QuizResponse updateQuiz(Long id, UpdateQuizRequest request) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.QUIZ_NOT_FOUND, id.toString())));

        if (request.getTitle() != null) {
            quiz.setTitle(request.getTitle());
        }

        if (request.getDescription() != null) {
            quiz.setDescription(request.getDescription());
        }

        if (request.getDurationMinutes() != null) {
            quiz.setDurationMinutes(request.getDurationMinutes());
        }

        if (request.getIsActive() != null) {
            quiz.setIsActive(request.getIsActive());
        }

        Quiz updatedQuiz = quizRepository.save(quiz);
        return modelMapper.map(updatedQuiz, QuizResponse.class);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteQuiz(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.QUIZ_NOT_FOUND, id.toString())));
        quiz.setIsActive(false);
        quizRepository.save(quiz);
    }

    @Override
    @Transactional(readOnly = true)
    public QuizResponse getQuizWithQuestionsAndAnswers(Long id) {
        Quiz quiz = quizRepository.findByIdWithQuestionsAndAnswers(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.QUIZ_NOT_FOUND, id.toString())));
        return modelMapper.map(quiz, QuizResponse.class);
    }
}
