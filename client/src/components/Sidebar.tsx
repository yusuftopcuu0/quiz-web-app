import { MdOutlineDashboard } from 'react-icons/md';
import { LuNotebookPen } from 'react-icons/lu';
import { FaChartBar } from 'react-icons/fa';
import { IoSettingsSharp } from 'react-icons/io5';
import { IoIosLogOut } from 'react-icons/io';
import { useNavigate } from 'react-router-dom';

import '../css/Dashboard.css';
import { useState } from 'react';
import { GiHamburgerMenu } from 'react-icons/gi';

function Sidebar() {
  const navigate = useNavigate();

  const [isOpen, setIsOpen] = useState(false);
  const [activeButton, setActiveButton] = useState('dashboard');
  const [isVisible, setIsVisible] = useState(false);

  const handleToggleSidebar = () => {
    if (!isVisible) {
      setIsVisible(true);
      setIsOpen(true);
    } else {
      setIsOpen(false);
      setIsVisible(false);
    }
  };

  return (
    <>
      <div className="sm:hidden bg-white absolute top-16 left-0 w-full z-50 text-center flex ps-5 gap-2 py-1">
        <button
          onClick={handleToggleSidebar}
          className="text-2xl transition-transform duration-300 hover:scale-110"
        >
          <GiHamburgerMenu />
        </button>
        <h1 className="text-lg font-bold">Kenar Çubuğunu {isOpen ? 'Kapat' : 'Aç'}</h1>
      </div>

      <aside
        className={`${
          isOpen ? 'flex' : 'hidden'
        } sm:flex sm:w-1/4 flex-col items-center gap-2 p-3 sm:static z-50 h-full sm:mx-4 md:mx-0 lg:w-1/4 mt-6 sm:mt-2`}
      >
        <div className="text-center">
          <div className="">
            <button
              onClick={() => {
                setActiveButton('dashboard');
                navigate('/');
              }}
              className={`flex items-center gap-2 justify-start rounded-lg text-black mb-5 dashboard-left-buttons w-full ${activeButton === 'dashboard' ? 'bg-green-500 text-white' : ''}`}
            >
              <MdOutlineDashboard />
              Dashboard
            </button>
            <hr className="pb-3" />
            <button
              onClick={() => {
                setActiveButton('exams');
                navigate('/');
              }}
              className={`flex items-center gap-2 justify-start rounded-lg text-black mb-5 dashboard-left-buttons ${activeButton === 'exams' ? 'bg-green-500 text-white' : ''} `}
            >
              <LuNotebookPen />
              Sınavlar
            </button>
            <hr className="pb-3" />
            <button
              onClick={() => {
                setActiveButton('statistics');
                navigate('/');
              }}
              className={`flex items-center gap-2 justify-start rounded-lg text-black mb-5 dashboard-left-buttons ${activeButton === 'statistics' ? 'bg-green-500 text-white' : ''}`}
            >
              <FaChartBar />
              İstatistikler
            </button>
          </div>

          <div className="" style={{ position: 'absolute', bottom: '0' }}>
            <div className="">
              <button
                onClick={() => {
                  setActiveButton('settings');
                  navigate('/');
                }}
                className={`flex items-center gap-2 justify-start rounded-lg text-black mb-4 dashboard-left-buttons ${activeButton === 'settings' ? 'bg-green-500 text-white' : ''}`}
              >
                <IoSettingsSharp />
                Ayarlar
              </button>
              <hr className="pb-3" />
            </div>

            <div>
              <button
                className="flex items-center gap-2 justify-start rounded-lg text-black mb-5 dashboard-left-buttons"
                onClick={() => navigate('/')}
              >
                <IoIosLogOut />
                Çıkış Yap
              </button>
            </div>
          </div>
        </div>
      </aside>
    </>
  );
}

export default Sidebar;
