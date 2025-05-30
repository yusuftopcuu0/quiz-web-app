import HomePage from '@/pages/HomePage.tsx';
import { ROUTES } from '@/constant/routes.ts';
import Signup from '@/pages/Signup.tsx';
import ErrorPage from '@/pages/error/ErrorPage.tsx';
import ProtectedPage from '@/pages/protected/ProtectedPage.tsx';
import type { RouteObject } from 'react-router-dom';
import MainLayout from '@/layout/MainLayout.tsx';
import TermsAndConditions from '@/pages/TermsAndConditions';

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
            path: ROUTES.SIGNUP,
            element: <Signup />,
          },
          {
            path: ROUTES.TERMS_AND_CONDITIONS,
            element: <TermsAndConditions />,
          },
        ],
      },
    ],
  },
];

export default mainNavigation;
