import { AppStateModel } from './app.state';
import { localStorageUserField } from '../app.const';

export function appInitialState(): AppStateModel {
  return {
    user: JSON.parse(localStorage.getItem(localStorageUserField)),
    firebaseToken: null
  }
}
