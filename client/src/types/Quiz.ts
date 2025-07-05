export interface QuizResponse {
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
