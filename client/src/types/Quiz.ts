export interface QuizResponse {
  id: number;
  title: string;
  description: string;
  durationMinutes: number;
  isActive: boolean;
  createdAt: string;
  updatedAt: string;
  createdBy: {
    id: number;
    username: string;
    email: string;
    roles: string[];
    createdAt: string;
    updatedAt: string;
  };
  questions: Question[];
}

interface Answer {
  id: number;
  content: string;
  isCorrect: boolean;
  createdAt: string;
  updatedAt: string;
}

enum QuestionType {
  MULTIPLE_CHOICE = 'MULTIPLE_CHOICE',
  TRUE_FALSE = 'TRUE_FALSE',
  SHORT_ANSWER = 'SHORT_ANSWER',
}
