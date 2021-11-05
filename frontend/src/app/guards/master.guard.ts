import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanLoad, Route, RouterStateSnapshot, UrlSegment } from '@angular/router';
import { UserRole } from '@models/user-role';
import { Store } from '@ngxs/store'
import { Observable } from 'rxjs'
import { canLoadOrActivate } from '@utils/guard-util'

@Injectable({
  providedIn: 'root'
})
export class MasterGuard implements CanLoad, CanActivate {
  constructor(private store: Store) {
  }

  canLoad(route: Route, segments: UrlSegment[]): Observable<boolean> | boolean {
    return canLoadOrActivate(this.store, UserRole.MASTER)
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
    return canLoadOrActivate(this.store, UserRole.MASTER)
  }
}
