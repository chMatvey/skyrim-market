import { UserRole } from './user-role';

export interface User {
  username: string
  password?: string
  role: UserRole
  token: string
}
