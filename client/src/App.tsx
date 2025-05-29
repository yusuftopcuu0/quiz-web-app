import { RouterProvider } from 'react-router-dom';
import router from '@/routes/index';

function App() {
  console.log('router', router);

  return (
    <div className="min-h-screen bg-gray-50">
      <RouterProvider router={router} />
    </div>
  );
}

export default App;
