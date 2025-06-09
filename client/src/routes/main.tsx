import HomePage from '@/pages/HomePage.tsx';
import { ROUTES } from '@/constant/routes.ts';
import Signup from '@/pages/Signup.tsx';
import ErrorPage from '@/pages/error/ErrorPage.tsx';
import ProtectedPage from '@/pages/protected/ProtectedPage.tsx';
import type { RouteObject } from 'react-router-dom';
import MainLayout from '@/layout/MainLayout.tsx';
import Login from '@/pages/Login';
import Dashboard from '@/pages/dashboard/Dashboard';

const mainNavigation: RouteObject[] = [
  {
    element: <ProtectedPage />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: ROUTES.HOME,
        element: <MainLayout />,
        children: [
          {
            path: ROUTES.HOME,
            element: <HomePage />,
            errorElement: <ErrorPage />,
          },
          {
            path: ROUTES.LOGIN,
            element: <Login />,
          },
          {
            path: ROUTES.SIGNUP,
            element: <Signup />,
            errorElement: <ErrorPage />,
          },
          {
            path: ROUTES.DASHBOARD,
            element: <Dashboard />,
          },
        ],
      },
    ],
  },
];

export default mainNavigation;
