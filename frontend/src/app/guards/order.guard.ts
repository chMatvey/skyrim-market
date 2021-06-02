import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { Store } from '@ngxs/store';
import { ClientState } from '@state/client/client.state';
import { map, tap } from 'rxjs/operators';
import { Navigate } from '@ngxs/router-plugin';

@Injectable({
  providedIn: 'root'
})
export class OrderGuard implements CanActivate {

  constructor(private store: Store) {
  }

  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): Observable<boolean> {
    return this.store.selectOnce(ClientState.orderType).pipe(
      map(value => !!value),
      tap(value => !value && this.store.dispatch([new Navigate(['/client/order'])]))
    )
  }
}
