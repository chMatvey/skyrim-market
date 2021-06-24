import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { AuthService } from '@services/auth.service';
import { Router } from '@angular/router';
import { Toolbar } from '@models/toolbar';
import { getToolbarStateByUserRole } from '@utils/toolbar';
import { User } from '@models/user';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ToolbarComponent implements OnInit {

  toolbar: Toolbar

  constructor(private authService: AuthService,
              private router: Router) {
  }

  get user(): User {
    return this.authService.user
  }


  ngOnInit() {
    this.toolbar = getToolbarStateByUserRole(this.user.role)
  }

  onLogout() {
    this.authService.logout()
      .subscribe(() => this.router.navigate(['/login']))
  }
}
