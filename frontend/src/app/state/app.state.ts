import { Action, State, StateContext } from '@ngxs/store';
import { SetUsername } from './app.actions';

export interface AppStateModel {
  username: string;
}

@State<AppStateModel>({
  name: 'app'
})
export class AppState {

  @Action(SetUsername)
  setUserName({patchState}: StateContext<AppStateModel>, {payload}: SetUsername) {
    patchState({username: payload})
  }
}
