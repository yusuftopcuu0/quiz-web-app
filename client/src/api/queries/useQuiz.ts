import * as service from '../services/quiz.service.ts';
import { useQuery } from '@tanstack/react-query';
import type { QuizResponse } from '@/types/Quiz.ts';

export function useGetAllQuizzes() {
  return useQuery<QuizResponse[], Error>({
    queryKey: ['getAllQuizzes'],
    queryFn: service.getAllQuizzes,
  });
}

export function useGetQuizById(quizId: number) {
  return useQuery<QuizResponse[], Error>({
    queryKey: ['getQuizById', quizId],
    queryFn: () => service.getQuizById(quizId),
    enabled: !!quizId,
  });
}

// return useQuery<QuizResponse[], Error>({
//   queryKey: ['getQuizById'],
// });
