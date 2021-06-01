import { UserRole } from './user-role';

export interface User {
  id: number
  username: string
  password?: string
  role?: UserRole
  token?: string
}
