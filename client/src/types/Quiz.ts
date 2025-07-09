export interface QuizResponse {
  id: number;
  title: string;
  description: string;
  durationMinutes: number;
  isActive: boolean;
  createdAt: string;
  updatedAt: string;
  createdBy: createdBy[];
  questions: Questions[];
}

interface createdBy {
  id: number;
  username: string;
  email: string;
  roles: string[];
  createdAt: string;
  updatedAt: string;
}

interface Questions {
  id: number;
  content: string;
  questionType: QuestionType;
  points: number;
  createdAt: string;
  updatedAt: string;
  answers: Answer[];
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

export interface CreateQuizRequest {
  title: string;
  description: string;
  durationMinutes: number;
  isActive: boolean;
  questions: Questions[];
}

export interface UpdateQuizRequest {
  id: number;
  title: string;
  description: string;
  durationMinutes: number;
  isActive: boolean;
  questions: Questions[];
}
