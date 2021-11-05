import { NotificationPopupComponent } from '@app/shared/notification-popup/notification-popup.component'
import { MatDialog } from '@angular/material/dialog'
import { ErrorPopupComponent } from '@app/shared/error-popup/error-popup.component'

export function showNotification(dialogService: MatDialog, data: string) {
  dialogService.open(NotificationPopupComponent, {
    data,
    panelClass: 'skyrim-popup'
  })
}

export function showError(dialogService: MatDialog, data: string) {
  dialogService.open(ErrorPopupComponent, {
    data,
    panelClass: 'skyrim-popup'
  })
}
