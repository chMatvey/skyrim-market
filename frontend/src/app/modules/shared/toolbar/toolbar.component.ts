import { ChangeDetectionStrategy, Component } from '@angular/core';
import { Select, Store } from '@ngxs/store';
import { Observable } from 'rxjs';
import { AppState, AppStateModel } from '@state/app.state';
import { AuthService } from '@services/auth.service';
import { Navigate } from '@ngxs/router-plugin';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ToolbarComponent {

  @Select(AppState)
  state$: Observable<AppStateModel>

  constructor(private store: Store,
              private authService: AuthService) {
  }

  onLogout() {
    this.authService.logout()
      .subscribe(() => this.store.dispatch([
        new Navigate(['/login'])
      ]))
  }
}
