import { BaseComponent } from '@app/shared/base/base.component'
import { Entity } from '@models/entity'
import { Observable } from 'rxjs'
import { MatDialog } from '@angular/material/dialog'
import { withLoading } from '@utils/loading-util'
import { tap } from 'rxjs/operators'
import { showNotification } from '@utils/notification-util'

export abstract class FormComponent extends BaseComponent {
  loading: boolean

  constructor(protected dialogService: MatDialog) {
    super()
  }

  compareEntity(a: Entity, b: Entity): boolean {
    return a?.id === b?.id
  }

  sendForm<T>(request: Observable<T>, notificationMessage: string): Observable<T> {
    return request
      .pipe(
        withLoading(this),
        tap(() => showNotification(this.dialogService, notificationMessage))
      )
  }
}
