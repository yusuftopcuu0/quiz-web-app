import { useState } from 'react';
import { useGetAllQuizzes, useDeleteQuiz, useCreateQuiz } from '@/api/queries/useQuiz';
import { ROUTES } from '@/constant/routes';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import '../css/Spinner.css';
import type { QuizResponse } from '@/types/Quiz';
import { updateQuiz } from '@/api/services/quiz.service';

const Quizzes = () => {
  const { mutateAsync: createQuiz } = useCreateQuiz();

  const { data: quizzes, isLoading, error } = useGetAllQuizzes();
  const [updatePanel, setUpdatePanel] = useState(false);
  const [createPanel, setCreatePanel] = useState(false);
  const [selectedQuiz, setSelectedQuiz] = useState<QuizResponse | null>(null);
  const [newQuiz, setNewQuiz] = useState<Partial<QuizResponse>>({
    title: '',
    description: '',
    durationMinutes: 0,
  });

  const navigate = useNavigate();
  const deleteQuiz = useDeleteQuiz();

  const handleUpdateQuiz = (quiz: QuizResponse) => {
    setSelectedQuiz(quiz);
    setUpdatePanel(true);
  };

  const handleCreateQuiz = () => {
    setCreatePanel(true);
  };

  const handleCreateInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setNewQuiz(prev => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSubmitCreate = async () => {
    try {
      if (!newQuiz.title || !newQuiz.description || !newQuiz.durationMinutes) {
        return;
      }

      await createQuiz({
        title: newQuiz.title,
        description: newQuiz.description,
        durationMinutes: newQuiz.durationMinutes,
        isActive: true,
        questions: [],
      });
      toast.success('Quiz oluşturuldu!');
      setCreatePanel(false);
      setNewQuiz({
        title: '',
        description: '',
        durationMinutes: 0,
      });
    } catch {
      toast.error('Quiz oluşturulamadı.');
    }
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (selectedQuiz) {
      setSelectedQuiz({ ...selectedQuiz, [e.target.name]: e.target.value });
    }
  };

  const handleSubmitUpdate = async () => {
    if (selectedQuiz) {
      try {
        await updateQuiz(selectedQuiz);
        setUpdatePanel(false);
        setSelectedQuiz(null);
      } catch {
        toast.error('Güncelleme başarısız.');
        console.error('Güncelleme başarısız.');
      }
    }
  };

  const handleDeleteQuiz = (quizId: number) => {
    if (window.confirm('Bu quizi silmek istediğinizden emin misiniz?')) {
      deleteQuiz.mutate(quizId);
    }
  };

  if (!localStorage.getItem('user')) {
    navigate(ROUTES.LOGIN);

    return null;
  }

  if (isLoading) return <div className="spinner"></div>;

  if (error) {
    toast.error('Quizzes could not be loaded');

    return <div>Error loading quizzes</div>;
  }

  return (
    <>
      <div className="flex flex-col gap-4">
        <h1 className="text-center">Quizzes ({quizzes?.length})</h1>
        <hr />

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 p-4">
          {quizzes?.map(quiz => (
            <div
              key={quiz.id}
              className="bg-white rounded-lg shadow-md p-4 hover:shadow-lg transition-shadow"
            >
              <h2 className="text-lg font-semibold mb-2">{quiz.title}</h2>
              <p className="text-gray-600">{quiz.description}</p>
              <div className="mt-4 flex gap-2">
                <button
                  className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
                  onClick={() => navigate(`/quiz/${quiz.id}`)}
                >
                  Sınava Başla
                </button>
                <button
                  className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
                  onClick={() => handleUpdateQuiz(quiz)}
                >
                  Düzenle
                </button>
                <button
                  className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
                  onClick={() => handleDeleteQuiz(quiz.id)}
                >
                  Sil
                </button>
              </div>
            </div>
          ))}
        </div>

        <button
          className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 mx-auto w-full"
          onClick={handleCreateQuiz}
        >
          Quiz Oluştur
        </button>
      </div>

      {updatePanel && selectedQuiz && (
        <div className="fixed top-0 right-0 w-full max-w-md h-full bg-white shadow-xl p-6 overflow-y-auto z-50">
          <h2 className="text-xl font-semibold mb-4">Quiz Düzenle</h2>
          <div className="flex flex-col gap-4">
            <input
              type="text"
              name="title"
              className="p-2 border border-gray-300 rounded"
              placeholder="Başlık"
              value={selectedQuiz.title}
              onChange={handleInputChange}
            />
            <input
              type="text"
              name="description"
              className="p-2 border border-gray-300 rounded"
              placeholder="Açıklama"
              value={selectedQuiz.description}
              onChange={handleInputChange}
            />
            <input
              type="number"
              name="durationMinutes"
              className="p-2 border border-gray-300 rounded"
              placeholder="Süre (dakika)"
              value={selectedQuiz.durationMinutes}
              onChange={handleInputChange}
            />
            <div className="flex gap-2 justify-end">
              <button
                className="bg-gray-400 text-white px-4 py-2 rounded hover:bg-gray-500"
                onClick={() => {
                  setUpdatePanel(false);
                  setSelectedQuiz(null);
                }}
              >
                Vazgeç
              </button>
              <button
                className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
                onClick={handleSubmitUpdate}
              >
                Güncellemeyi Kaydet
              </button>
            </div>
          </div>
        </div>
      )}

      {createPanel && (
        <div className="fixed top-0 right-0 w-full max-w-md h-full bg-white shadow-xl p-6 overflow-y-auto z-50">
          <h2 className="text-xl font-semibold mb-4">Yeni Quiz Oluştur</h2>
          <div className="flex flex-col gap-4">
            <input
              type="text"
              name="title"
              className="p-2 border border-gray-300 rounded"
              placeholder="Başlık"
              value={newQuiz.title}
              onChange={handleCreateInputChange}
            />
            <input
              type="text"
              name="description"
              className="p-2 border border-gray-300 rounded"
              placeholder="Açıklama"
              value={newQuiz.description}
              onChange={handleCreateInputChange}
            />
            <input
              type="number"
              name="durationMinutes"
              className="p-2 border border-gray-300 rounded"
              placeholder="Süre (dakika)"
              value={newQuiz.durationMinutes}
              onChange={handleCreateInputChange}
            />
            <div className="flex gap-2 justify-end">
              <button
                className="bg-gray-400 text-white px-4 py-2 rounded hover:bg-gray-500"
                onClick={() => {
                  setCreatePanel(false);
                  setNewQuiz({
                    title: '',
                    description: '',
                    durationMinutes: 0,
                  });
                }}
              >
                Vazgeç
              </button>
              <button
                className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
                onClick={handleSubmitCreate}
              >
                Oluştur
              </button>
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default Quizzes;
