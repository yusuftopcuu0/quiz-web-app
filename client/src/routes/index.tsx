import type { RouteObject } from 'react-router-dom';
import { createBrowserRouter } from 'react-router-dom';
import mainNavigation from './main.tsx';

const index: RouteObject[] = [];

index.push(...mainNavigation);

export default createBrowserRouter(index);
