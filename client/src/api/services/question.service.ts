import { unwrapResponse } from '@/utils/api.ts';
import { getApiClient } from '@/api/client.ts';
import type { Question } from '@/types/Question.ts';

const baseUrl = '/questions';

export function getAllQuestions() {
  return unwrapResponse<Question[]>(getApiClient().get(baseUrl));
}

export function getQuestionById(questionId: number) {
  return unwrapResponse<Question[]>(getApiClient().get(`${baseUrl}/${questionId}`));
}

export function createQuestion(question: Question) {
  return unwrapResponse<Question>(getApiClient().post(baseUrl, question));
}

export function deleteQuestion(questionId: number) {
  return unwrapResponse<Question>(getApiClient().delete(`${baseUrl}/${questionId}`));
}
