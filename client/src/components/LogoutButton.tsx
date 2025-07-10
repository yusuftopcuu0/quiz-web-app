import { ROUTES } from '@/constant/routes';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { useLogout } from '@/api/queries/useAuth';

function LogoutButton() {
  const navigate = useNavigate();
  const { mutateAsync: logout } = useLogout();

  const handleLogout = async () => {
    try {
      await logout();
      toast.success('Çıkış başarılı! Yönlendiriliyorsunuz.', {
        autoClose: 2500,
      });

      setTimeout(() => {
        navigate(ROUTES.HOME);
      }, 3000);
    } catch (error: any) {
      console.error('Çıkış hatası:', error);
      toast.error('Çıkış başarısız. Lütfen tekrar deneyin.');
    }
  };

  return (
    <div>
      <button onClick={handleLogout}>Çıkış Yap</button>
    </div>
  );
}

export default LogoutButton;
