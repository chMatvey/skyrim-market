import { Action, Selector, State, StateContext } from '@ngxs/store';
import { SetToolbar, SetUser } from './app.actions';
import { User } from '@models/user';
import { Injectable } from '@angular/core';
import { getInitialState } from './initial-state';
import { Toolbar } from '@models/toolbar';

export interface AppStateModel {
  user: User
  toolbar: Toolbar
}

@State<AppStateModel>({
  defaults: getInitialState(),
  name: 'app'
})
@Injectable()
export class AppState {

  @Action(SetUser)
  setUser({patchState}: StateContext<AppStateModel>, {payload}: SetUser) {
    patchState({user: payload})
  }

  @Action(SetToolbar)
  setToolbar({patchState}: StateContext<AppStateModel>, {payload}: SetToolbar) {
    patchState({toolbar: payload})
  }

  @Selector()
  static username(state: AppStateModel) {
    return state.user.username
  }
}
