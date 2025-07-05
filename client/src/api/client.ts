import axios from 'axios';
import { errorToast } from '@/utils/toast.ts';
import { ROUTES } from '@/constant/routes.ts';
import type { AuthResponses } from '@/types/Auth.ts';

const API_URL = 'https://quiz-app-g16u.onrender.com';

export const getApiClient = (isPublic: boolean = false) => {
  console.log('import.meta.env.VITE_API_URL', API_URL);
  const API_URL_FIXED = isPublic ? `${API_URL}/public/api` : API_URL + '/api';
  const client = axios.create({
    baseURL: API_URL_FIXED,
    headers: {
      'Content-Type': 'application/json',
    },
  });

  client.interceptors.request.use(
    config => {
      const user = JSON.parse(localStorage.getItem('user') || '{}') as AuthResponses;
      if (user) {
        config.headers.Authorization = `Bearer ${user.accessToken}`;
      }

      return config;
    },
    error => {
      return Promise.reject(error);
    }
  );

  client.interceptors.response.use(
    response => {
      const user = JSON.parse(localStorage.getItem('user') || '{}') as AuthResponses;
      if (!user) {
        window.location.href = ROUTES.LOGIN;
      }

      return response;
    },
    error => {
      errorToast(
        `${error.response?.data?.errorMessage || error.response?.data?.title || error.message}`
      );
      console.error('error', error);

      return Promise.reject(error);
    }
  );

  return client;
};
