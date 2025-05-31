import HomePage from '@/pages/HomePage.tsx';
import { ROUTES } from '@/constant/routes.ts';
import Signup from '@/pages/Signup.tsx';
import ErrorPage from '@/pages/error/ErrorPage.tsx';
import ProtectedPage from '@/pages/protected/ProtectedPage.tsx';
import type { RouteObject } from 'react-router-dom';
import MainLayout from '@/layout/MainLayout.tsx';
import Login from '@/pages/Login';
import Dashboard from '@/pages/dashboard/Dashboard';
import Sidebar from '@/components/Sidebar';
import SidebarLayout from '@/layout/SidebarLayout';

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
          },
          {
            path: ROUTES.LOGIN,
            element: <Login />,
          },
          {
            path: ROUTES.SIGNUP,
            element: <Signup />,
          },
          {
            path: ROUTES.DASHBOARD,
            element: <Dashboard />,
          },
        ],
      },

      {
        path: ROUTES.DASHBOARD,
        element: <SidebarLayout />,
        children: [
          {
            path: ROUTES.DASHBOARD,
            element: <Sidebar />,
          },
        ],
      },
    ],
  },
];

export default mainNavigation;
