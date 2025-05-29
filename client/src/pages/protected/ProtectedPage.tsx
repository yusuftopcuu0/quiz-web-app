import { Navigate, Outlet, useLocation } from 'react-router-dom';
import { ROUTES } from '@/constant/routes.ts';

export default function ProtectedPage() {
  const user = JSON.parse(localStorage.getItem('quiz_app_user') || '{}');
  const location = useLocation();

  return !user.token ? (
    <Outlet />
  ) : (
    <Navigate to={ROUTES.LOGIN} replace state={{ from: location.pathname }} />
  );
}
