import { UserRole } from './user-role';

export interface User {
  id: number
  username: string
  password?: string
  confirmPassword?: string
  role: UserRole
  accessToken?: string
  refreshToken?: string
}
