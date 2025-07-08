import { unwrapResponse } from '@/utils/api.ts';
import { getApiClient } from '@/api/client.ts';
import type { QuizResponse } from '@/types/Quiz.ts';

const baseUrl = '/quizzes';

export function getAllQuizzes() {
  return unwrapResponse<QuizResponse[]>(getApiClient().get(baseUrl));
}

export function getQuizById(quizId: number) {
  return unwrapResponse<QuizResponse[]>(getApiClient().get(`${baseUrl}/${quizId}`));
}

export function createQuiz(quiz: QuizResponse) {
  return unwrapResponse<QuizResponse[]>(getApiClient().post(baseUrl, quiz));
}

export function updateQuiz(quiz: QuizResponse) {
  return unwrapResponse<QuizResponse[]>(getApiClient().put(`${baseUrl}/${quiz.id}`, quiz));
}

export function deleteQuiz(quizId: number) {
  return unwrapResponse<QuizResponse[]>(getApiClient().delete(`${baseUrl}/${quizId}`));
}
