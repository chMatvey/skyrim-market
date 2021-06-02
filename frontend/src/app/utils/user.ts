import { UserRole } from '@models/user-role';
import { Store } from '@ngxs/store';
import { User } from '@models/user';

export function getUrlByUserRole(role: UserRole): string {
  switch (role) {
    case UserRole.CLIENT:
      return 'client'
    case UserRole.EMPLOYEE:
      return 'employee'
    case UserRole.MASTER:
      return 'master'
    default:
      throw new Error('Unknown Role')
  }
}

export function userFromStore(store: Store): User {
  const state = store.snapshot()
  return state.app.user
}
