import { useState } from 'react';
import { useRegister } from '@/api/queries/useAuth';
import { ToastContainer } from 'react-toastify';

function Signup() {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const { mutateAsync: register, isPending } = useRegister();

  const handleRegister = async () => {
    if (isPending) {
      return;
    }

    await register({
      username,
      email,
      password,
    });
  };

  return (
    <div className="flex flex-col justify-center" style={{ height: '100vh' }}>
      <ToastContainer />
      <div className="py-10 mx-5 rounded" style={{ backgroundColor: '#f3f3f3' }}>
        <h1 className="text-3xl font-bold text-center pt-5">Kayıt Ol</h1>

        <div className="mx-5">
          <label className="block mb-2 text-lg font-medium text-gray-900 dark:text-black">
            Kullanıcı Adı
          </label>
          <div className="flex">
            <span className="inline-flex items-center px-3 text-sm text-gray-900 bg-gray-200 border rounded-s-md dark:bg-blue-600 dark:text-gray-400 dark:border-gray-600">
              👤
            </span>
            <input
              type="text"
              className="rounded-none rounded-e-lg bg-gray-50 border text-gray-900 block flex-1 w-full text-sm p-2.5 dark:bg-white"
              placeholder="Kullanıcı adınız..."
              value={username}
              onChange={e => setUsername(e.target.value)}
            />
          </div>

          <label className="block mb-2 text-lg font-medium text-gray-900 dark:text-black mt-7">
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
            Kayıt Ol
          </button>
        </div>
      </div>
    </div>
  );
}

export default Signup;
