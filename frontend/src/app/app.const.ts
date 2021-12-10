import { environment } from '../environments/environment';

export const isProd = environment.production
export const isDev = !isProd

export const apiUrl = `${environment.server}/api`

export const localStorageUserField = 'skyrim-user'
export const localStorageOrderMessagesField = 'orderMessages'
