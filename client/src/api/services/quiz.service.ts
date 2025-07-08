import { unwrapResponse } from '@/utils/api.ts';
import { getApiClient } from '@/api/client.ts';
import type { QuizResponse } from '@/types/Quiz.ts';

const baseUrl = '/quizzes';

export function getAllQuizzes() {
  return unwrapResponse<QuizResponse[]>(getApiClient().get(baseUrl));
}
