import { AppStateModel } from './app.state';
import { localStorageUserField } from '../app.const';
import { getToolbarStateByUserRole } from '@utils/toolbar';

export function getInitialState(): AppStateModel {
  const user = JSON.parse(localStorage.getItem(localStorageUserField))
  return {
    user,
    toolbar: getToolbarStateByUserRole(user?.role)
  }
}
