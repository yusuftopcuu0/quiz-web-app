import { toast } from 'react-toastify';

export const successToast = (message: string, autoClose: number = 2500) => {
  toast.success(message, { autoClose });
};

export const warningToast = (message: string, autoClose: number = 2500) => {
  toast.warning(message, { autoClose });
};

export const errorToast = (message: string, autoClose: number = 2500) => {
  toast.error(message, { autoClose });
};
