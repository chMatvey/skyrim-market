import { Action, Selector, State, StateContext } from '@ngxs/store';
import { SetUser } from './app.actions';
import { User } from '../models/user';
import { Injectable } from '@angular/core';
import { getInitialState } from './initial-state';

export interface AppStateModel {
  user: User
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

  @Selector()
  static username(state: AppStateModel) {
    return state.user.username
  }
}
