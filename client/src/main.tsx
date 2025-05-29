import { createRoot } from 'react-dom/client'
import './index.css'
import './App.css'
import App from './App.tsx'
import './css/footer.css'
import './css/Navbar.css'
import { BrowserRouter } from 'react-router-dom'

createRoot(document.getElementById('root')!).render(
  <BrowserRouter>
    <App />
  </BrowserRouter>,
)
