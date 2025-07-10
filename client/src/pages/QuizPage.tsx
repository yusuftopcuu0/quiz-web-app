import { useParams } from 'react-router-dom';
import { useGetQuizById } from '@/api/queries/useQuiz';

function QuizPage() {
  const { id } = useParams();
  //   const { data: quiz, isLoading } = useGetQuizById(Number(id));
  const { data: isLoading } = useGetQuizById(Number(id));

  if (isLoading) {
    return <div>Yükleniyor...</div>;
  }

  return (
    <div className="max-w-2xl mx-auto p-6">
      <h2 className="text-2xl font-bold mb-6">Sınav Başlığı</h2>

      <div className="mb-4">
        <p className="text-gray-700 font-medium">Soru 3 / 10</p>
        <p className="mt-2 text-lg">Bu bir örnek soru metnidir. Doğru cevabı seçiniz.</p>
      </div>

      <div className="space-y-3 mb-6">
        <div className="p-4 border rounded-lg hover:bg-gray-100 cursor-pointer">Seçenek A</div>
        <div className="p-4 border rounded-lg hover:bg-gray-100 cursor-pointer bg-blue-100 border-blue-400">
          Seçenek B (seçili örneği)
        </div>
        <div className="p-4 border rounded-lg hover:bg-gray-100 cursor-pointer">Seçenek C</div>
        <div className="p-4 border rounded-lg hover:bg-gray-100 cursor-pointer">Seçenek D</div>
      </div>

      <div className="flex justify-end">
        <button className="bg-blue-600 hover:bg-blue-700 text-white font-semibold px-6 py-2 rounded disabled:opacity-50">
          Sonraki Soru
        </button>
      </div>
    </div>
  );
}

export default QuizPage;
