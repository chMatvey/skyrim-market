import { Injectable } from '@angular/core';
import { CanLoad, Route, Router, UrlSegment } from '@angular/router';
import { AuthService } from '@services/auth.service';
import { UserRole } from '@models/user-role';
import { getUrlByUserRole } from '@utils/user';

@Injectable({
  providedIn: 'root'
})
export class MasterGuard implements CanLoad {

  constructor(private authService: AuthService,
              private router: Router) {
  }

  canLoad(route: Route, segments: UrlSegment[]): boolean {
    const role = this.authService.user?.role

    if (!role) {
      this.router.navigate(['/login'])
      return false
    } else if (role !== UserRole.MASTER) {
      const location = getUrlByUserRole(role)
      this.router.navigate([`/${location}`])
      return false
    }

    return true
  }
}
