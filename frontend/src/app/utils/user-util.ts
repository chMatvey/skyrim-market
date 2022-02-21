import { UserRole } from '@models/user-role';
import { User } from '@models/user';

export function getUrlByUserRole(role: UserRole): string {
  switch (role) {
    case UserRole.CLIENT:
      return 'client'
    case UserRole.EMPLOYEE:
      return 'employee'
    case UserRole.MASTER:
      return 'master'
    case UserRole.STUDENT:
      return 'student'
    default:
      throw new Error('Неизвестная роль')
  }
}

export function createFormData(user: User): FormData {
  const {username, password} = user
  const formData = new FormData()
  formData.append("username", username)
  formData.append("password", password)

  return formData;
}
