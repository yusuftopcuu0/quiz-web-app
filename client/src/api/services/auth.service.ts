import { unwrapResponse } from '@/utils/api.ts';
import type { AuthRequestBody, AuthResponses } from '@/types/Auth.ts';
import { getApiClient } from '@/api/client.ts';

const baseUrl = '/auth';

export function login(body: AuthRequestBody) {
  console.log('url', `${baseUrl}/login`);

  return unwrapResponse<AuthResponses>(getApiClient(true).post(`${baseUrl}/login`, body));
}
