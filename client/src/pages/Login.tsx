import { useState } from 'react';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from 'react-toastify';
import { toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';
import { ROUTES } from '../constant/routes';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const navigate = useNavigate();

  const handleLogin = () => {
    const trimmedEmail = email.trim();
    const trimmedPassword = password.trim();

    try {
      if (trimmedEmail !== '' && trimmedPassword !== '' && trimmedPassword.length >= 6) {
        toast.success('Giriş Başarılı. Yönlendiriliyorsunuz.', {
          autoClose: 2500,
        });
        setTimeout(() => {
          navigate(ROUTES.DASHBOARD);
        }, 3000);
      } else {
        toast.error('Hatalı Giriş', {
          autoClose: 3500,
        });
      }
    } catch (error) {
      if (error instanceof Error) {
        console.error('Hata mesajı: ' + error.message);
        toast.error('Hata mesajı: ' + error.message);
      } else {
        console.error('Bilinmeyen bir hata oluştu.');
      }
    }
  };

  return (
    <div className="flex flex-col justify-center" style={{ height: '100vh' }}>
      <ToastContainer />
      <div className="py-10 mx-5 rounded" id="login-content" style={{ backgroundColor: '#f3f3f3' }}>
        <div>
          <h1 className="text-3xl font-bold text-center pt-5">Giriş Yap</h1>
        </div>

        <div className="mx-5">
          <label
            htmlFor="input-group-1"
            className="block mb-2 text-lg font-medium text-gray-900 dark:text-black"
          >
            E-Mail
          </label>
          <div className="flex">
            <span className="inline-flex items-center px-3 text-sm text-gray-900 bg-gray-200 border rounded-e-0 border-gray-300 border-e-0 rounded-s-md dark:bg-blue-600 dark:text-gray-400 dark:border-gray-600">
              <svg
                className="w-4 h-4 text-gray-500 dark:text-white"
                aria-hidden="true"
                xmlns="http://www.w3.org/2000/svg"
                fill="currentColor"
                viewBox="0 0 20 20"
              >
                <path d="m10.036 8.278 9.258-7.79A1.979 1.979 0 0 0 18 0H2A1.987 1.987 0 0 0 .641.541l9.395 7.737Z" />
                <path d="M11.241 9.817c-.36.275-.801.425-1.255.427-.428 0-.845-.138-1.187-.395L0 2.6V14a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2V2.5l-8.759 7.317Z" />
              </svg>
            </span>
            <input
              type="email"
              className="rounded-none rounded-e-lg bg-gray-50 border text-gray-900 focus:ring-blue-500 focus:border-blue-500 block flex-1 min-w-0 w-full text-sm border-gray-300 p-2.5  dark:bg-white dark:border-gray-600 dark:placeholder-gray-400 dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="E-mailiniz..."
              value={email}
              onChange={e => setEmail(e.target.value)}
            />
          </div>

          <label
            htmlFor="website-admin"
            className="block mb-2 text-lg font-medium text-gray-900 dark:text-black mt-7"
          >
            Şifre
          </label>
          <div className="flex">
            <span className="inline-flex items-center px-3 text-sm text-gray-900 bg-gray-200 border rounded-e-0 border-gray-300 border-e-0 rounded-s-md dark:bg-red-600 dark:text-gray-400 dark:border-gray-600">
              <svg
                className="w-4 h-4 text-gray-500 dark:text-white"
                aria-hidden="true"
                xmlns="http://www.w3.org/2000/svg"
                fill="currentColor"
                viewBox="0 0 20 20"
              >
                <path d="M10 0a10 10 0 1 0 10 10A10.011 10.011 0 0 0 10 0Zm0 5a3 3 0 1 1 0 6 3 3 0 0 1 0-6Zm0 13a8.949 8.949 0 0 1-4.951-1.488A3.987 3.987 0 0 1 9 13h2a3.987 3.987 0 0 1 3.951 3.512A8.949 8.949 0 0 1 10 18Z" />
              </svg>
            </span>
            <input
              type="password"
              className="rounded-none rounded-e-lg bg-gray-50 border text-gray-900 focus:ring-blue-500 focus:border-blue-500 block flex-1 min-w-0 w-full text-sm border-gray-300 p-2.5  dark:bg-white dark:border-gray-600 dark:placeholder-gray-400 dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="Şifreniz..."
              value={password}
              onChange={e => setPassword(e.target.value)}
            />
          </div>
          <div>
            <button
              onClick={handleLogin}
              className="bg-green-500 rounded text-white p-2 mt-5 hover:bg-green-600 w-full"
            >
              Giriş Yap
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
