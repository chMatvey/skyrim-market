import { User } from '@models/user';

export namespace App {
  export class Login {
    static readonly type = '[App] login'
    constructor(public user: User) {}
  }

  export class Logout {
    static readonly type = '[App] logout'
    constructor() {}
  }
}
