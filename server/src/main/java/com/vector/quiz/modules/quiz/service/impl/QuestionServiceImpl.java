package com.vector.quiz.modules.quiz.service.impl;

import com.vector.quiz.common.service.AuthenticationService;
import com.vector.quiz.modules.quiz.dto.request.CreateAnswerRequest;
import com.vector.quiz.modules.quiz.dto.request.CreateQuestionRequest;
import com.vector.quiz.modules.quiz.dto.response.QuestionResponse;
import com.vector.quiz.modules.quiz.entity.Answer;
import com.vector.quiz.modules.quiz.entity.Question;
import com.vector.quiz.modules.quiz.entity.Quiz;
import com.vector.quiz.common.exception.BaseException;
import com.vector.quiz.common.exception.ErrorMessage;
import com.vector.quiz.common.exception.MessageType;

import com.vector.quiz.modules.quiz.repository.QuestionRepository;
import com.vector.quiz.modules.quiz.repository.QuizRepository;
import com.vector.quiz.modules.quiz.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final ModelMapper modelMapper;
    private final AuthenticationService authenticationService;

    @Override
    @Transactional(readOnly = true)
    public List<QuestionResponse> getQuestionsByQuizId(Long quizId) {
        if (!quizRepository.existsById(quizId)) {
            throw new BaseException(new ErrorMessage(MessageType.QUIZ_NOT_FOUND, quizId.toString()));
        }

        return questionRepository.findByQuizId(quizId).stream()
                .map(question -> modelMapper.map(question, QuestionResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public QuestionResponse getQuestionById(Long quizId, Long questionId) {
        if (!quizRepository.existsById(quizId)) {
            throw new BaseException(new ErrorMessage(MessageType.QUIZ_NOT_FOUND, quizId.toString()));
        }

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.QUESTION_NOT_FOUND, questionId.toString())));

        if (!question.getQuiz().getId().equals(quizId)) {
            throw new BaseException(new ErrorMessage(MessageType.QUESTION_NOT_FOUND, questionId.toString()));
        }

        return modelMapper.map(question, QuestionResponse.class);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public QuestionResponse addQuestionToQuiz(Long quizId, CreateQuestionRequest request) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.QUIZ_NOT_FOUND, quizId.toString())));

        Question question = new Question();
        question.setQuiz(quiz);
        question.setContent(request.getContent());
        question.setQuestionType(request.getQuestionType());
        question.setPoints(request.getPoints());

        // Add answers
        if (request.getAnswers() != null) {
            for (CreateAnswerRequest answerRequest : request.getAnswers()) {
                Answer answer = new Answer();
                answer.setContent(answerRequest.getContent());
                answer.setIsCorrect(answerRequest.isCorrect());
                question.addAnswer(answer);
            }
        }

        quiz.addQuestion(question);
        Question savedQuestion = questionRepository.save(question);
        return modelMapper.map(savedQuestion, QuestionResponse.class);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void removeQuestionFromQuiz(Long quizId, Long questionId) {
        if (!quizRepository.existsById(quizId)) {
            throw new BaseException(new ErrorMessage(MessageType.QUIZ_NOT_FOUND, quizId.toString()));
        }

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.QUESTION_NOT_FOUND, questionId.toString())));

        if (!question.getQuiz().getId().equals(quizId)) {
            throw new BaseException(new ErrorMessage(MessageType.QUESTION_NOT_FOUND, questionId.toString()));
        }

        questionRepository.delete(question);
    }
}
