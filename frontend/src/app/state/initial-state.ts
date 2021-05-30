import { AppStateModel } from './app.state';
import { getToolbarStateByUserRole } from '../utils/toolbar';
import { localStorageUserField } from '../app.const';

export function getInitialState(): AppStateModel {
  const user = JSON.parse(localStorage.getItem(localStorageUserField))
  return {
    user,
    toolbar: getToolbarStateByUserRole(user?.role)
  }
}
