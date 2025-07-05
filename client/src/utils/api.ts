import type { BaseResponse } from '@/types/core/api.ts';

export async function unwrapResponse<T>(promise: Promise<{ data: BaseResponse<T> }>): Promise<T> {
  const { data } = await promise;

  return data.data || ([] as T);
}
