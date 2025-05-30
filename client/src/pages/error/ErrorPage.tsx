import { useNavigate } from 'react-router-dom';

export default function ErrorPage() {
  const navigate = useNavigate();

  return (
    <div className="h-screen flex flex-col justify-center items-center bg-gray-100 text-gray-800 text-center px-4">
      <h1 className="text-5xl font-bold mb-4">Oops!</h1>
      <p className="text-lg mb-6">Bir şeyler yanlış gitti. Lütfen daha sonra tekrar deneyin.</p>

      <p className="text-lg mb-5 font-semibold">Hata sebebi: " "</p>

      <button
        onClick={() => navigate('/')}
        className="px-6 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 transition"
      >
        Ana Sayfaya Dön
      </button>
    </div>
  );
}
