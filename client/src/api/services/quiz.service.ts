import { unwrapResponse } from '@/utils/api.ts';
import { getApiClient } from '@/api/client.ts';
import type { CreateQuizRequest, QuizResponse, UpdateQuizRequest } from '@/types/Quiz.ts';

const baseUrl = '/quizzes';

export function getAllQuizzes() {
  return unwrapResponse<QuizResponse[]>(getApiClient().get(baseUrl));
}

export function getQuizById(quizId: number) {
  return unwrapResponse<QuizResponse[]>(getApiClient().get(`${baseUrl}/${quizId}`));
}

export function createQuiz(params: CreateQuizRequest) {
  return unwrapResponse<QuizResponse>(getApiClient().post(baseUrl, params));
}

export function updateQuiz(params: UpdateQuizRequest) {
  return unwrapResponse<QuizResponse>(getApiClient().put(`${baseUrl}/${params.id}`, params));
}

export function deleteQuiz(quizId: number) {
  return unwrapResponse<QuizResponse>(getApiClient().delete(`${baseUrl}/${quizId}`));
}
