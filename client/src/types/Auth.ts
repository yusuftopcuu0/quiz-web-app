export interface AuthRequestBody {
  username?: string | null;
  email?: string | null;
  password: 'string';
}

export interface AuthResponses {
  id: number;
  createdAt: Date;
  updatedAt: Date;
  username: string;
  email: string;
  role: string;
  accessToken: string;
  refreshToken: string;
}
