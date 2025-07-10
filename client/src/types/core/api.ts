import type { AxiosError } from 'axios';

export type AxiosErrorMessage = AxiosError<{ message: string }>;

export interface BaseResponse<T> {
  isSuccess: boolean;
  errorMessage: string;
  payload: T | null;
}
