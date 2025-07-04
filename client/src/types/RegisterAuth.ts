export interface AuthRequestBody {
  username: string
  email: string
  password: string
}

export interface AuthResponses {
  id: number
  createdAt: Date
  updatedAt: Date
  username: string
  email: string
}
