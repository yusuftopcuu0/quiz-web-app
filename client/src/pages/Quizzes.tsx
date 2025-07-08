import { useGetAllQuizzes } from '@/api/queries/useQuiz';
import { ROUTES } from '@/constant/routes';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

const Quizzes = () => {
  const navigate = useNavigate();
  const { data: quizzes, isLoading, error } = useGetAllQuizzes();

  if (!localStorage.getItem('user')) {
    navigate(ROUTES.LOGIN);

    return null;
  }

  if (isLoading) return <div>Loading...</div>;

  if (error) {
    toast.error('Quizzes could not be loaded');

    return <div>Error loading quizzes</div>;
  }

  console.log('quiz', quizzes);

  return (
    <div className="flex flex-col gap-4">
      <h1 className="text-center">Quizzes</h1>

      <hr />

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 p-4">
        {quizzes?.map(quiz => (
          <div
            key={quiz.id}
            className="bg-white rounded-lg shadow-md p-4 hover:shadow-lg transition-shadow"
          >
            <h2 className="text-lg font-semibold mb-2">{quiz.title}</h2>
            <p className="text-gray-600">{quiz.description}</p>
            <div className="mt-4">
              <button
                className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
                onClick={() => navigate(`/quiz/${quiz.id}`)}
              >
                Start Quiz
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Quizzes;
