import { Outlet } from 'react-router-dom';
import Navbar from '@/components/Navbar.tsx';
// import Sidebar from '@/components/Sidebar';

export default function MainLayout() {
  return (
    <div className="flex flex-col min-h-screen">
      <Navbar />
      <div className="flex flex-1">
        {/* <Sidebar /> */}
        <div className="flex-1 bg-gray-100 p-4">
          <Outlet />
        </div>
      </div>
    </div>
  );
}
