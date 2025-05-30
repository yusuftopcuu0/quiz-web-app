import { Link } from 'react-router-dom';

const TermsAndConditions = () => {
  return (
    <div className="min-h-screen bg-gray-100 p-8 text-gray-800">
      <div className="max-w-3xl mx-auto bg-white p-6 rounded-lg shadow-md">
        <h1 className="text-3xl font-bold mb-4 text-center">Şartlar ve Koşullar</h1>
        <p className="mb-4">Uygulamamızı kullanarak aşağıdaki şartları kabul etmiş sayılırsınız.</p>

        <h2 className="text-xl font-semibold mt-6 mb-2">1. Hizmet Kullanımı</h2>
        <p className="mb-4">
          QuizApp, kullanıcıların sınavlara hazırlanmasına yardımcı olmak için tasarlanmış bir
          platformdur. Hizmeti kötüye kullanmak yasaktır.
        </p>

        <h2 className="text-xl font-semibold mt-6 mb-2">2. Veri Toplama</h2>
        <p className="mb-4">
          Kayıt sırasında verdiğiniz bilgiler sadece uygulama işlevleri için kullanılır ve üçüncü
          kişilerle paylaşılmaz.
        </p>

        <h2 className="text-xl font-semibold mt-6 mb-2">3. Sorumluluk Reddi</h2>
        <p className="mb-4">
          QuizApp üzerinden sunulan içeriklerin doğruluğu konusunda azami özen gösterilse de,
          hatasızlık garantisi verilmez.
        </p>

        <h2 className="text-xl font-semibold mt-6 mb-2">4. Değişiklik Hakkı</h2>
        <p className="mb-4">
          Bu şartlar önceden haber vermeksizin değiştirilebilir. Güncellemeleri düzenli olarak
          kontrol etmeniz gerekir.
        </p>

        <div className="text-center mt-6">
          <Link to="/register" className="text-blue-600 hover:underline">
            Geri Dön ve Kayıt Ol
          </Link>
        </div>
      </div>
    </div>
  );
};

export default TermsAndConditions;
