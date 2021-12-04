import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { Toolbar } from '@models/template/toolbar';
import { getToolbarStateByUserRole } from '@utils/toolbar-util';
import { User } from '@models/user';
import { Select, Store } from '@ngxs/store'
import { App } from '@state/app.actions'
import { Navigate } from '@ngxs/router-plugin'
import { Observable } from 'rxjs'
import { AppState } from '@state/app.state'
import { map } from 'rxjs/operators'
import Logout = App.Logout
import { MatDialog } from '@angular/material/dialog'
import { showError } from '@utils/notification-util'

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ToolbarComponent implements OnInit {
  @Select(AppState.user)
  user$: Observable<User>

  toolbar$: Observable<Toolbar>

  constructor(private store: Store,
              private dialogService: MatDialog) {
  }

  ngOnInit() {
    this.toolbar$ = this.user$.pipe(map(user => getToolbarStateByUserRole(user?.role)))
  }

  onLogout() {
    this.store.dispatch(new Logout())
      .subscribe(
        () => this.store.dispatch(new Navigate(['/login'])),
        error => showError(this.dialogService, error)
      )
  }
}
