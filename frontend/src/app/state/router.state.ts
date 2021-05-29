import { Action, State, StateContext } from '@ngxs/store';
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';

export class Navigate {
  static readonly type = '[router] navigate'
  constructor(public payload: string) {}
}

@State<string>({
  name: 'router',
  defaults: ''
})
@Injectable()
export class RouterState {
  constructor(private router: Router) {
  }

  @Action(Navigate)
  async changeRouter(context: StateContext<string>, action: Navigate) {
    const path = action.payload
    await this.router.navigateByUrl(path)
    context.setState(path)
  }
}
