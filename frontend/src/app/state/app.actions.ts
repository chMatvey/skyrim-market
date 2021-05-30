import { User } from '@models/user';
import { Toolbar } from '@models/toolbar';

export class SetUser {
  static readonly type = '[app] set user'
  constructor(public payload: User) {}
}

export class SetToolbar {
  static readonly type = '[app] set toolbar'
  constructor(public payload: Toolbar) {
  }
}
