import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { UserService } from '@services/user.service';
import { createRegistrationForm } from '@app/auth/login/login';
import { withLoading } from '@utils/loading-util';
import { MatDialog } from '@angular/material/dialog';
import { tap } from 'rxjs/operators';
import { Store } from '@ngxs/store'
import { Navigate } from '@ngxs/router-plugin'
import { showError, showNotification } from '@utils/notification-util'
import { toMessage } from '@utils/http-util'
import { User } from '@models/user'

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  form: FormGroup

  loading = false

  constructor(private store: Store,
              private authService: UserService,
              private dialogService: MatDialog) {
  }

  ngOnInit(): void {
    this.form = createRegistrationForm()
  }

  submit() {
    const user: User = this.form.value;

    if (user.password !== user.confirmPassword) {
      showNotification(this.dialogService, 'Пароль и подтверждение пароля не совпадают')
      return;
    }

    this.authService.register(user)
      .pipe(
        withLoading(this),
        tap(() => showNotification(this.dialogService, 'Аккаунт успешно создан!'))
      )
      .subscribe(
        () => this.store.dispatch(new Navigate(['/login'])),
        error => showError(this.dialogService, toMessage(error))
      )
  }
}
