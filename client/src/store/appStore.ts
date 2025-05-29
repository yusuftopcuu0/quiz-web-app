import { create } from 'zustand';

type AppState = {
  darkMode: boolean;
  setDarkMode: (value: boolean) => void;
};

export const useAppStore = create<AppState>(set => ({
  darkMode: localStorage.getItem('darkMode') === 'true',
  setDarkMode: (value: boolean) => {
    localStorage.setItem('darkMode', value.toString());
    set({ darkMode: value });
  },
}));
