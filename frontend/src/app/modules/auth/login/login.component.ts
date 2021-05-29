import { Component, OnInit } from '@angular/core';
import { Store } from '@ngxs/store';
import { FormGroup } from '@angular/forms';
import { createLoginForm } from './login';
import { AuthService } from '../../../services/auth.service';
import { SetToolbarLinks, SetUser } from '../../../state/app.actions';
import { Navigate } from '../../../state/router.state';
import { withLoading } from '../../../utils/stream-pipe-operators';
import { getUrlByUserRole } from '../../../utils/user';
import { getToolbarLinksByUserRole } from '../../../utils/toolbar';

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
        new Navigate(getUrlByUserRole(user.role)),
        new SetToolbarLinks(getToolbarLinksByUserRole(user.role))
      ]))

  }
}
