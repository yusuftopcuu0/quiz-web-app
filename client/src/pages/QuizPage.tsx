import { useParams } from 'react-router-dom';
import { useGetQuizById } from '@/api/queries/useQuiz';
import { useState } from 'react';

function QuizPage() {
  const { id } = useParams() as { id: string };
  const { data: quiz, isLoading, error } = useGetQuizById(Number(id));
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
  const [selectedOption, setSelectedOption] = useState<number | null>(null);

  if (isLoading) {
    return <div className="flex items-center justify-center h-screen">Yükleniyor...</div>;
  }

  if (error) {
    return (
      <div className="flex items-center justify-center h-screen text-red-500">
        Sınav yüklenirken bir hata oluştu
      </div>
    );
  }

  if (!quiz) {
    return <div className="flex items-center justify-center h-screen">Quiz bulunamadı</div>;
  }

  const currentQuestion = quiz.questions[currentQuestionIndex];

  const handleOptionSelect = (optionIndex: number) => {
    setSelectedOption(optionIndex);
  };

  console.log('quiz title: ', quiz.title);

  return (
    <div className="min-h-screen flex flex-col">
      <div className="max-w-2xl mx-auto p-6">
        <h2 className="text-3xl font-bold mb-6">{quiz.title}</h2>

        <div className="bg-white rounded-lg shadow-md p-6 mb-8">
          <div className="mb-4">
            <p className="text-gray-600 font-medium">
              Soru {currentQuestionIndex + 1} / {quiz.questions.length}
            </p>
            <p className="mt-2 text-xl font-semibold">{currentQuestion.content}</p>
          </div>

          <div className="space-y-4">
            {currentQuestion.answers.map((answer, index) => (
              <button
                key={index}
                onClick={() => handleOptionSelect(index)}
                className={`w-full p-3 rounded-lg transition-colors ${
                  selectedOption === index
                    ? 'bg-blue-500 text-white'
                    : 'bg-gray-100 hover:bg-gray-200'
                }`}
              >
                {answer.content}
              </button>
            ))}
          </div>

          <div className="mt-8 flex justify-between">
            <button
              className="px-4 py-2 bg-gray-200 rounded-lg hover:bg-gray-300"
              onClick={() => setCurrentQuestionIndex(prev => Math.max(0, prev - 1))}
              disabled={currentQuestionIndex === 0}
            >
              Önceki Soru
            </button>
            <button
              className="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600"
              onClick={() => {
                if (selectedOption !== null) {
                  setCurrentQuestionIndex(prev => Math.min(quiz.questions.length - 1, prev + 1));
                  setSelectedOption(null);
                }
              }}
              disabled={
                currentQuestionIndex === quiz.questions.length - 1 || selectedOption === null
              }
            >
              {currentQuestionIndex === quiz.questions.length - 1
                ? 'Testi Bitir'
                : 'Bir Sonraki Soru'}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default QuizPage;
