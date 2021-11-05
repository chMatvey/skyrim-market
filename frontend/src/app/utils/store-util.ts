import { Store } from '@ngxs/store'
import { User } from '@models/user'
import { AppState } from '@state/app.state'

export function userFromStore(store: Store): User {
  return store.selectSnapshot<User>(AppState.user)
}

export function userIdFromStore(store: Store): number {
  return store.selectSnapshot<User>(AppState.user)?.id
}
