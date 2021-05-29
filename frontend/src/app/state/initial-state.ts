import { AppStateModel } from './app.state';

export function getInitialState(): AppStateModel {
  const user = JSON.parse(localStorage.getItem('current-user'))

  return {user}
}
