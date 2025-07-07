import { useMutation } from '@tanstack/react-query';
import * as service from '../services/auth.service.ts';
import { useNavigate } from 'react-router-dom';
import { ROUTES } from '@/constant/routes.ts';

export const useLogin = () => {
  const navigate = useNavigate();

  return useMutation({
    mutationFn: service.login,
    onSuccess: data => {
      localStorage.setItem('user', JSON.stringify(data));
      navigate(ROUTES.DASHBOARD);
    },
  });
};

export const useRegister = () => {
  const navigate = useNavigate();

  return useMutation({
    mutationFn: service.register,
    onSuccess: () => {
      navigate(ROUTES.LOGIN);
    },
  });
};

export const useLogout = () => {
  return useMutation({
    mutationFn: service.logout,
    onSuccess: () => {
      localStorage.removeItem('user');
      localStorage.removeItem('accessToken');
    },
  });
};
