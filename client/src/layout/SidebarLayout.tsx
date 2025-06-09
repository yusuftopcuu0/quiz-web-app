import Navbar from '@/components/Navbar';
import Sidebar from '@/components/Sidebar';
import Dashboard from '@/pages/dashboard/Dashboard';

export default function SidebarLayout() {
  return (
    <div className="flex flex-col min-h-screen">
      <Navbar />
      <div className="flex">
        <Sidebar />
        <div className="flex-1 bg-gray-100"></div>
        <Dashboard />
      </div>
    </div>
  );
}
