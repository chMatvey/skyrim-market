import { AppStateModel } from './app.state';
import { getToolbarLinksByUserRole } from '../utils/toolbar';

export function getInitialState(): AppStateModel {
  const user = JSON.parse(localStorage.getItem('current-user'))
  return !!user ? {user, toolbarLinks: getToolbarLinksByUserRole(user.role)} : {}
}
