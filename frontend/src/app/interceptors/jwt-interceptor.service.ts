import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Store } from '@ngxs/store'
import { TOKEN_PREFIX } from '@utils/security-util'
import { userFromStore } from '@utils/store-util'

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private store: Store) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token = userFromStore(this.store)?.accessToken;
    if (token) {
      request = request.clone({
        setHeaders: {
          Authorization: `${TOKEN_PREFIX}${token}`
        }
      })
      return next.handle(request);
    } else {
      return next.handle(request);
    }
  }
}
