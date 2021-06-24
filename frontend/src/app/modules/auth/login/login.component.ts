import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { createLoginForm } from './login';
import { AuthService } from '@services/auth.service';
import { withLoading } from '@utils/stream-pipe-operators';
import { getUrlByUserRole } from '@utils/user';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: FormGroup

  loading = false

  error: boolean

  constructor(private router: Router,
              private authService: AuthService) {
  }

  ngOnInit(): void {
    this.form = createLoginForm()
  }

  submit() {
    this.authService.login(this.form.value)
      .pipe(
        withLoading(this),
        catchError(err => {
          this.error = true
          throw err
        })
      )
      .subscribe(user => this.router.navigate([getUrlByUserRole(user.role)]))
  }
}
