import { Action, Selector, State, StateContext } from '@ngxs/store';
import { User } from '@models/user';
import { Injectable } from '@angular/core';
import { appInitialState } from './initial-state';
import { App } from '@state/app.actions'
import { UserService } from '@services/user.service'
import { tap } from 'rxjs/operators'
import { localStorageUserField } from '@app/app.const'
import { Observable } from 'rxjs'
import Login = App.Login
import Logout = App.Logout

export interface AppStateModel {
  user: User
}

@State<AppStateModel>({
  name: 'app',
  defaults: appInitialState()
})
@Injectable()
export class AppState {
  constructor(private userService: UserService) {
  }

  @Selector()
  static user(state: AppStateModel): User {
    return state.user
  }

  @Action(Login, {cancelUncompleted: true})
  login(ctx: StateContext<AppStateModel>, {user}: Login): Observable<User> {
    return this.userService.login(user).pipe(
      tap(user => {
        localStorage.setItem(localStorageUserField, JSON.stringify(user))
        const state = ctx.getState()
        ctx.setState({...state, user})
      })
    )
  }

  @Action(Logout, {cancelUncompleted: true})
  logout({patchState}: StateContext<AppStateModel>): Observable<void> {
    return this.userService.logout().pipe(
      tap(() => {
        localStorage.removeItem(localStorageUserField)
        patchState({user: null})
      })
    )
  }
}
