import { UserRole } from '@models/user-role'
import { Navigate } from '@ngxs/router-plugin'
import { getUrlByUserRole } from '@utils/user-util'
import { Store } from '@ngxs/store'
import { userFromStore } from '@utils/store-util'

export function canLoadOrActivate(store: Store, ROLE: UserRole): boolean {
  const role = userFromStore(store)?.role
  if (!role) {
    store.dispatch(new Navigate(['/login']))
    return false
  } else if (role !== ROLE) {
    store.dispatch(new Navigate([getUrlByUserRole(role)]))
    return false
  } else {
    return true
  }
}
