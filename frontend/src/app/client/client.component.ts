import { Component, OnDestroy, OnInit } from '@angular/core';
import { MessageService } from '@services/message/message.service'
import { MatDialog } from '@angular/material/dialog'
import { showError } from '@utils/notification-util'
import { toMessage } from '@utils/firebase-util'
import { Store } from '@ngxs/store'
import { Client } from '@state/client/client.actions'
import { takeUntil } from 'rxjs/operators'
import { BaseComponent } from '@app/shared/base/base.component'
import { OrderStatusEnum } from '@models/order-status-enum'
import firebase from 'firebase'
import { AngularFireMessaging } from '@angular/fire/messaging'
import AddOrderMessage = Client.AddOrderMessage
import MessagePayload = firebase.messaging.MessagePayload

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.scss']
})
export class ClientComponent extends BaseComponent implements OnInit, OnDestroy {
  private broadcast = new BroadcastChannel('channel-123')

  constructor(private messageService: MessageService,
              private messaging: AngularFireMessaging,
              private dialogService: MatDialog,
              private store: Store) {
    super()
  }

  ngOnInit(): void {
    this.messageService.requestPermission()
      .subscribe(
        () => {},
        error => showError(this.dialogService, toMessage(error))
      )

    this.broadcast.onmessage = (event) => this.handleMessage(event.data as MessagePayload)

    this.messaging.messages.pipe(takeUntil(this.ngUnsubscribe))
        .subscribe((message: MessagePayload) => this.handleMessage(message))
  }

  ngOnDestroy() {
    super.ngOnDestroy()
    this.broadcast.close()
  }

  private handleMessage(message: MessagePayload) {
    const messageData = {
      orderId: Number.parseInt(message.data['orderId'], 10),
      orderStatus: message.data['orderStatus'] as OrderStatusEnum
    }
    this.store.dispatch(new AddOrderMessage(messageData))
  }
}
