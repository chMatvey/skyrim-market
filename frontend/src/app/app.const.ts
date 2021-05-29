import { environment } from '../environments/environment';

export const isProd = environment.production
export const isDev = !isProd
