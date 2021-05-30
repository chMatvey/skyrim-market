import { Component, OnInit } from '@angular/core';
import { Store } from '@ngxs/store';
import { FormGroup } from '@angular/forms';
import { createLoginForm } from './login';
import { AuthService } from '@services/auth.service';
import { withLoading } from '@utils/stream-pipe-operators';
import { getUrlByUserRole } from '@utils/user';
import { getToolbarStateByUserRole } from '@utils/toolbar';
import { Navigate } from '@ngxs/router-plugin';
import { SetToolbar, SetUser } from '@state/app.actions';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: FormGroup

  loading = false

  constructor(private store: Store, private authService: AuthService) {
  }

  ngOnInit(): void {
    this.form = createLoginForm()
  }

  submit() {
    this.authService.login(this.form.value)
      .pipe(
        withLoading(this)
      )
      .subscribe(user => this.store.dispatch([
        new SetUser(user),
        new SetToolbar(getToolbarStateByUserRole(user.role)),
        new Navigate([getUrlByUserRole(user.role)])
      ]))

  }
}
