import { useState } from 'react';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer, toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';
import { ROUTES } from '../constant/routes';
import axios from 'axios';
import type { AuthResponses } from '@/types/Auth';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin = async () => {
    console.log('object');
    try {
      const response = await axios.post<AuthResponses>(
        'https://quiz-app-g16u.onrender.com/public/api/auth/login',
        {
          username: email,
          password,
        }
      );

      toast.success('Giriş başarılı! Yönlendiriliyorsunuz.', {
        autoClose: 2500,
      });

      localStorage.setItem('accessToken', response.data.accessToken);

      setTimeout(() => {
        navigate(ROUTES.DASHBOARD);
      }, 3000);
    } catch (error: any) {
      console.error('Giriş hatası:', error);
      const message = error?.response?.data?.message || 'Giriş başarısız. Lütfen tekrar deneyin.';
      toast.error(message, { autoClose: 3500 });
    }
  };

  return (
    <div className="flex flex-col justify-center" style={{ height: '100vh' }}>
      <ToastContainer />
      <div className="py-10 mx-5 rounded" id="login-content" style={{ backgroundColor: '#f3f3f3' }}>
        <h1 className="text-3xl font-bold text-center pt-5">Giriş Yap</h1>

        <div className="mx-5">
          <label className="block mb-2 text-lg font-medium text-gray-900 dark:text-black">
            E-Mail
          </label>
          <div className="flex">
            <span className="inline-flex items-center px-3 text-sm text-gray-900 bg-gray-200 border rounded-s-md dark:bg-blue-600 dark:text-gray-400 dark:border-gray-600">
              📧
            </span>
            <input
              type="email"
              className="rounded-none rounded-e-lg bg-gray-50 border text-gray-900 block flex-1 w-full text-sm p-2.5 dark:bg-white"
              placeholder="E-mailiniz..."
              value={email}
              onChange={e => setEmail(e.target.value)}
            />
          </div>

          <label className="block mb-2 text-lg font-medium text-gray-900 dark:text-black mt-7">
            Şifre
          </label>
          <div className="flex">
            <span className="inline-flex items-center px-3 text-sm text-gray-900 bg-gray-200 border rounded-s-md dark:bg-red-600 dark:text-gray-400 dark:border-gray-600">
              🔒
            </span>
            <input
              type="password"
              className="rounded-none rounded-e-lg bg-gray-50 border text-gray-900 block flex-1 w-full text-sm p-2.5 dark:bg-white"
              placeholder="Şifreniz..."
              value={password}
              onChange={e => setPassword(e.target.value)}
            />
          </div>

          <button
            onClick={handleLogin}
            className="bg-green-500 rounded text-white p-2 mt-5 hover:bg-green-600 w-full"
          >
            Giriş Yap
          </button>
        </div>
      </div>
    </div>
  );
}

export default Login;
