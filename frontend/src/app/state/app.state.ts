import { Action, State, StateContext } from '@ngxs/store';
import { SetUser } from './app.actions';
import { User } from '../models/user';

export interface AppStateModel {
  user: User
}

@State<AppStateModel>({
  name: 'app'
})
export class AppState {

  @Action(SetUser)
  setUserName({patchState}: StateContext<AppStateModel>, {payload}: SetUser) {
    patchState({user: payload})
  }
}
