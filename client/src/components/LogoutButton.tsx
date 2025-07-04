import { ROUTES } from '@/constant/routes';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import axios from 'axios';
import type { AuthResponses } from './types/Auth';

function LogoutButton() {
  const navigate = useNavigate();

  const handleLogout = async () => {
    try {
      const response = await axios.post<AuthResponses>(
        'https://quiz-app-g16u.onrender.com/rest/api/auth/logout'
        // {
        //    username: email,
        //    password,
        // }
      );

      toast.success('Çıkış başarılı! Yönlendiriliyorsunuz.', {
        autoClose: 2500,
      });

      localStorage.setItem('accessToken', response.data.accessToken);

      setTimeout(() => {
        navigate(ROUTES.HOME);
      }, 3000);
    } catch (error: any) {
      console.error('Giriş hatası:', error);
      const message =
        error?.response?.data?.payload?.join('') ||
        error?.response?.data?.errorMessage ||
        'Çıkış başarısız. Lütfen tekrar deneyin.';
      toast.error(message, { autoClose: 3500 });
    }
  };

  return (
    <div>
      <button onClick={handleLogout}>Çıkış Yap</button>
    </div>
  );
}

export default LogoutButton;
