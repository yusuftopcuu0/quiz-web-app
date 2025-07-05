import { RouterProvider } from 'react-router-dom';
import router from '@/routes/index';
import 'react-toastify/dist/ReactToastify.css';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
      retry: false,
    },
  },
});

function App() {
  console.log('router', router);

  return (
    <QueryClientProvider client={queryClient}>
      <div className="min-h-screen bg-gray-50">
        <RouterProvider router={router} />
      </div>
    </QueryClientProvider>
  );
}

export default App;
