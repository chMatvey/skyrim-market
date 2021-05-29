import { User } from '../models/user';

export class SetUser {
  static readonly type = '[app] set user'
  constructor(public payload: User) {}
}
