import * as service from '../services/quiz.service.ts';
import { useMutation, useQuery } from '@tanstack/react-query';
import type { QuizResponse } from '@/types/Quiz.ts';
import { toast } from 'react-toastify';

export function useGetAllQuizzes() {
  return useQuery<QuizResponse[], Error>({
    queryKey: ['getAllQuizzes'],
    queryFn: service.getAllQuizzes,
  });
}

export function useGetQuizById(quizId: number) {
  return useQuery<QuizResponse, Error>({
    queryKey: ['getQuizById', quizId],
    queryFn: () => service.getQuizById(quizId),
    enabled: !!quizId,
  });
}

export function useCreateQuiz() {
  return useMutation({
    mutationFn: service.createQuiz,
    onSuccess: () => {
      toast.success('Quiz başarıyla oluşturuldu');
    },
  });
}

export function useUpdateQuiz(quiz: QuizResponse) {
  return useMutation({
    mutationFn: () => service.updateQuiz(quiz),
    onSuccess: () => {
      toast.success('Quiz başarıyla güncellendi');
    },
  });
}

export function useDeleteQuiz() {
  return useMutation({
    mutationFn: (quizId: number) => service.deleteQuiz(quizId),
    onSuccess: () => {
      toast.success('Quiz başarıyla silindi');
    },
  });
}
