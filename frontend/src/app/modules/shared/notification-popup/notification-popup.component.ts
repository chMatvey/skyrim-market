import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-notification-popup',
  templateUrl: './notification-popup.component.html',
  styleUrls: ['./notification-popup.component.scss']
})
export class NotificationPopupComponent {

  constructor(public dialogRef: MatDialogRef<NotificationPopupComponent>,
              @Inject(MAT_DIALOG_DATA) private data: string) { }

  get message(): string {
    return this.data
  }
}
