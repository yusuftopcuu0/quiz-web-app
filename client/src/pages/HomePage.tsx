import Navbar from '../components/Navbar'
import Footer from '../components/Footer'
import { Link } from 'react-router-dom'

const HomePage = () => {
  return (
    <>
      <div className="flex flex-col min-h-screen">
        <Navbar />

        <main className="flex flex-col items-center justify-center flex-grow p-6 bg-gradient-to-b from-blue-100 to-white">
          <h2 className="text-3xl md:text-4xl font-bold mb-4 text-center text-blue-700">
            Sınavlara Katıl, Sonuçlarını Takip Et!
          </h2>
          <p className="text-gray-700 text-center max-w-xl mb-6">
            Bu uygulama ile sınavlara kolayca katılabilir, doğru ve yanlış cevaplarını görebilir,
            istatistiklerini takip edebilirsin.
          </p>

          <div className="flex gap-4">
            <Link
              to="/auth/login"
              className="bg-blue-500 hover:bg-blue-600 text-white px-6 py-2 rounded shadow"
            >
              Giriş Yap
            </Link>
            <Link
              to="/auth/signup"
              className="bg-gray-200 hover:bg-gray-300 text-gray-700 px-6 py-2 rounded shadow"
            >
              Kayıt Ol
            </Link>
          </div>
        </main>

        <Footer />
      </div>
    </>
  )
}

export default HomePage
