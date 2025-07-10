import { unwrapResponse } from '@/utils/api.ts';
import type { AuthRequestBody, AuthResponses } from '@/types/Auth.ts';
import { getApiClient } from '@/api/client.ts';

const baseUrl = '/auth';

export function login(body: AuthRequestBody) {
  // console.log('url', `${baseUrl}/login`);

  return unwrapResponse<AuthResponses>(getApiClient(true).post(`${baseUrl}/login`, body));
}

export function register(body: AuthRequestBody) {
  console.log('url', `${baseUrl}/signup`);

  return unwrapResponse<AuthResponses>(getApiClient(true).post(`${baseUrl}/signup`, body));
}

export function logout() {
  return unwrapResponse(getApiClient(true).post(`${baseUrl}/logout`));
}
