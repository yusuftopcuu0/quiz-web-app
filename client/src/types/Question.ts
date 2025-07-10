export interface Question {
  id: number;
  content: string;
  questionType: QuestionType;
  points: number;
  createdAt: string;
  updatedAt: string;
  answers: answers[];
}

interface answers {
  id: number;
  content: string;
  isCorrect: boolean;
  createdAt: string;
  updatedAt: string;
}

export enum QuestionType {
  MULTIPLE_CHOICE = 'MULTIPLE_CHOICE',
  TRUE_FALSE = 'TRUE_FALSE',
  SHORT_ANSWER = 'SHORT_ANSWER',
}
