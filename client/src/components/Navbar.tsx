import { useState } from 'react'
import { NavLink } from 'react-router-dom'

const Navbar = () => {
  const [isOpen, setIsOpen] = useState(false)
  return (
    <nav className="bg-white border-gray-200 dark:bg-gray-900">
      <div className="max-w-screen-xl flex flex-wrap items-center justify-between mx-auto p-4">
        <a href="https://flowbite.com/" className="flex items-center space-x-3 rtl:space-x-reverse">
          <img
            src="https://flowbite.com/docs/images/logo.svg"
            className="h-8"
            alt="Flowbite Logo"
          />
          <span className="self-center text-2xl font-semibold whitespace-nowrap dark:text-white">
            QuizApp
          </span>
        </a>

        {/* Hamburger Button */}
        <button
          onClick={() => setIsOpen(!isOpen)}
          type="button"
          className="inline-flex items-center p-2 w-10 h-10 justify-center text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none dark:text-gray-400 dark:hover:bg-gray-700"
        >
          <span className="sr-only">Open main menu</span>
          <svg
            className="w-5 h-5"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 17 14"
          >
            <path
              stroke="currentColor"
              strokeLinecap="round"
              strokeLinejoin="round"
              strokeWidth="2"
              d="M1 1h15M1 7h15M1 13h15"
            />
          </svg>
        </button>

        {/* Menü */}
        <div
          className={`${isOpen ? 'block' : 'hidden'} w-full md:block md:w-auto`}
          id="navbar-default"
        >
          <ul className="font-medium flex flex-col p-4 md:p-0 mt-4 border border-gray-100 rounded-lg bg-gray-50 md:flex-row md:space-x-8 md:mt-0 md:border-0 md:bg-white dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700">
            <NavLink
              to="/"
              className={({ isActive }) =>
                `block py-2 px-3 rounded md:p-0 ${
                  isActive
                    ? 'text-blue-700 dark:text-blue-500'
                    : 'text-gray-900 hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 dark:text-white md:dark:hover:text-blue-500'
                }`
              }
            >
              Ana Sayfa
            </NavLink>

            <NavLink
              to="/auth/login"
              className={({ isActive }) =>
                `block py-2 px-3 rounded md:p-0 ${
                  isActive
                    ? 'text-blue-700 dark:text-blue-500'
                    : 'text-gray-900 hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 dark:text-white md:dark:hover:text-blue-500'
                }`
              }
            >
              Giriş Yap
            </NavLink>

            <NavLink
              to="/services"
              className={({ isActive }) =>
                `block py-2 px-3 rounded md:p-0 ${
                  isActive
                    ? 'text-blue-700 dark:text-blue-500'
                    : 'text-gray-900 hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 dark:text-white md:dark:hover:text-blue-500'
                }`
              }
            >
              Hakkımızda
            </NavLink>

            <NavLink
              to="/about"
              className={({ isActive }) =>
                `block py-2 px-3 rounded md:p-0 ${
                  isActive
                    ? 'text-blue-700 dark:text-blue-500'
                    : 'text-gray-900 hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 dark:text-white md:dark:hover:text-blue-500'
                }`
              }
            >
              Hakkımızda
            </NavLink>

            <NavLink
              to="/contact"
              className={({ isActive }) =>
                `block py-2 px-3 rounded md:p-0 ${
                  isActive
                    ? 'text-blue-700 dark:text-blue-500'
                    : 'text-gray-900 hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 dark:text-white md:dark:hover:text-blue-500'
                }`
              }
            >
              İletişim
            </NavLink>
          </ul>
        </div>
      </div>
    </nav>
  )
}

export default Navbar
