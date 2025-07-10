import * as service from '../services/question.service.ts';
import { useMutation, useQuery } from '@tanstack/react-query';
import type { Question } from '@/types/Question.ts';
import { toast } from 'react-toastify';

export function useGetAllQuestions() {
  return useQuery<Question[], Error>({
    queryKey: ['getAllQuestions'],
    queryFn: service.getAllQuestions,
  });
}

export function useGetQuestionById(questionId: number) {
  return useQuery<Question, Error>({
    queryKey: ['getQuestionById', questionId],
    queryFn: () =>
      service.getAllQuestions().then(questions => {
        const question = questions.find(q => q.id === questionId);
        if (!question) throw new Error('Soru bulunamadı');

        return question;
      }),
    enabled: !!questionId,
  });
}

export function useCreateQuestion() {
  return useMutation({
    mutationFn: service.createQuestion,
    onSuccess: () => {
      toast.success('Soru başarıyla oluşturuldu');
    },
  });
}

export function useDeleteQuestion() {
  return useMutation({
    mutationFn: service.deleteQuestion,
    onSuccess: () => {
      toast.success('Soru başarıyla silindi');
    },
  });
}
