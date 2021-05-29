import { User } from '../models/user';
import { ToolbarLink } from '../models/toolbar-link';

export class SetUser {
  static readonly type = '[app] set user'
  constructor(public payload: User) {}
}

export class SetToolbarLinks {
  static readonly type = '[app] set toolbar links'
  constructor(public payload: ToolbarLink[]) {
  }
}
