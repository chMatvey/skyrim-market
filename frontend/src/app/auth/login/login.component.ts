import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { createLoginForm } from './login';
import { getUrlByUserRole } from '@utils/user-util';
import { Store } from '@ngxs/store'
import { App } from '@state/app.actions'
import { Navigate } from '@ngxs/router-plugin'
import { MatDialog } from '@angular/material/dialog'
import { showError } from '@utils/notification-util'
import { toMessage } from '@utils/http-util'
import { withLoading } from '@utils/loading-util'
import Login = App.Login

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form: FormGroup

  loading = false

  error: boolean

  constructor(private store: Store,
              private dialogService: MatDialog) {
  }

  ngOnInit(): void {
    this.form = createLoginForm()
  }

  submit() {
    this.store.dispatch(new Login(this.form.value))
      .pipe(withLoading(this))
      .subscribe(
        state => this.store.dispatch(new Navigate([getUrlByUserRole(state.app.user.role)])),
        error => showError(this.dialogService, toMessage(error))
      )
  }
}
