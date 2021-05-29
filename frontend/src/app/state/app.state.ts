import { Action, Selector, State, StateContext } from '@ngxs/store';
import { SetToolbarLinks, SetUser } from './app.actions';
import { User } from '../models/user';
import { Injectable } from '@angular/core';
import { getInitialState } from './initial-state';
import { ToolbarLink } from '../models/toolbar-link';

export interface AppStateModel {
  user?: User
  toolbarLinks?: ToolbarLink[]
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

  @Action(SetToolbarLinks)
  setToolbarLinks({patchState}: StateContext<AppStateModel>, {payload}: SetToolbarLinks) {
    patchState({toolbarLinks: payload})
  }

  @Selector()
  static username(state: AppStateModel) {
    return state.user.username
  }

  @Selector()
  static toolbarLinks(state: AppStateModel) {
    return state.toolbarLinks
  }
}
