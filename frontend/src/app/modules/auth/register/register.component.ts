import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '@services/auth.service';
import { createLoginForm } from '@modules/auth/login/login';
import { withLoading } from '@utils/stream-pipe-operators';
import { MatDialog } from '@angular/material/dialog';
import { NotificationPopupComponent } from '@shared/notification-popup/notification-popup.component';
import { UserRole } from '@models/user-role';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  form: FormGroup

  loading = false

  constructor(private router: Router,
              private authService: AuthService,
              private dialogService: MatDialog) {
  }

  ngOnInit(): void {
    this.form = createLoginForm()
  }

  submit() {
    this.authService.register({...this.form.value, role: UserRole.CLIENT})
      .pipe(
        withLoading(this),
        tap(() => this.showNotification('Account successfully created!'))
      )
      .subscribe(
        () => this.router.navigate(['/login']),
        error => console.log(error)
      )
  }

  private showNotification(data: string) {
    this.dialogService.open(NotificationPopupComponent, {
      data,
      panelClass: 'skyrim-popup'
    })
  }
}
