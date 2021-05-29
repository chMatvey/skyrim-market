import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { Store } from '@ngxs/store';
import { User } from '../../../models/user';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  state$: Observable<User>

  constructor(private store: Store) {
    this.state$ = this.store.select(state => state.app)
      .pipe(filter(appState => appState.user))
  }
}
