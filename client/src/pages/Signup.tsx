import axios from 'axios'
import { useState } from 'react'
import { toast } from 'react-toastify'
import { useNavigate } from 'react-router-dom'
import { ROUTES } from '../constant/routes'
import type { AuthResponses } from '../types/RegisterAuth'
import 'react-toastify/dist/ReactToastify.css'

function Signup() {
  const [username, setUsername] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const navigate = useNavigate()

  const handleRegister = async (e: React.FormEvent) => {
    e.preventDefault()
    try {
      const response = await axios.post<AuthResponses>(
        'https://quiz-app-g16u.onrender.com/public/api/auth/signup',
        {
          username,
          email,
          password,
        },
      )

      toast.success('Kayıt başarılı! Giriş Sayfasına Yönlendiriliyorsunuz.', {
        autoClose: 2500,
      })

      console.log('Kayıt başarılı:', response.data)

      localStorage.setItem('user', JSON.stringify(response.data))

      setTimeout(() => {
        navigate(ROUTES.LOGIN)
      }, 3000)
    } catch (error: any) {
      console.error('Giriş hatası:', error)
      const message =
        error?.response?.data?.payload?.join('') ||
        error?.response?.data?.errorMessage ||
        'Kayıt başarısız. Lütfen tekrar deneyin.'
      toast.error(message, { autoClose: 3500 })
    }
  }
  return (
    <div className="signup-content m-5 rounded shadow-xl" style={{ backgroundColor: '#f0f0f0' }}>
      <div className="">
        <h1 className="text-4xl font-bold text-center pt-5">Kayıt Ol</h1>
      </div>

      <br />

      <hr />

      <div className="py-10">
        <form className="mx-5">
          <div className="grid gap-6 mb-6 md:grid-cols-2">
            <div>
              <label htmlFor="first_name" className="block mb-2 text-sm font-medium text-gray-900">
                Kullanıcı Adı
              </label>
              <input
                type="text"
                id="first_name"
                className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:border-gray-600 dark:placeholder-gray-400 dark:focus:ring-blue-500 dark:focus:border-blue-500"
                required
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
            </div>
          </div>
          <div className="mb-6">
            <label htmlFor="email" className="block mb-2 text-sm font-medium text-gray-900">
              E-mail Adres
            </label>
            <input
              type="email"
              id="email"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:border-gray-600 dark:placeholder-gray-400 dark:focus:ring-blue-500 dark:focus:border-blue-500"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div className="mb-6">
            <label htmlFor="password" className="block mb-2 text-sm font-medium text-gray-900">
              Şifre
            </label>
            <input
              type="password"
              id="password"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:border-gray-600 dark:placeholder-gray-400 dark:focus:ring-blue-500 dark:focus:border-blue-500"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>

          <div className="flex items-start mb-6">
            <div className="flex items-center h-5">
              <input
                id="remember"
                type="checkbox"
                className="w-4 h-4 border border-gray-300 rounded-sm bg-gray-50 focus:ring-3 focus:ring-blue-300 dark:border-gray-600 dark:focus:ring-blue-600 dark:ring-offset-gray-800"
                required
              />
            </div>
            <label htmlFor="remember" className="ms-2 text-sm font-medium text-gray-900">
              <a href="#" className="text-blue-600 hover:underline dark:text-blue-700 font-bold">
                Şartlar ve koşullar
              </a>
              'a Katılıyorum.
            </label>
          </div>
          <button
            onClick={handleRegister}
            type="button"
            className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
          >
            Gönder
          </button>
        </form>
      </div>
    </div>
  )
}

export default Signup
