import { createRoot } from 'react-dom/client';
import './index.css';
import './App.css';
import App from './App.tsx';
import './css/footer.css';

import 'primereact/resources/themes/lara-light-cyan/theme.css';

createRoot(document.getElementById('root')!).render(<App />);
