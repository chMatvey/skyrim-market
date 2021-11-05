import { Component, OnInit } from '@angular/core';
import { Order } from '@models/order/order';
import { withLoading } from '@utils/stream-pipe-operators';
import { ClientOrderService } from '@services/order/client-order.service'
import { Store } from '@ngxs/store'
import { userIdFromStore } from '@utils/store-util'
import { MatDialog } from '@angular/material/dialog'
import { showError } from '@utils/notification-util'
import { toMessage } from '@utils/http-util'
import { Navigate } from '@ngxs/router-plugin'

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {
  orders: Order[]

  loading: boolean

  constructor(private orderService: ClientOrderService,
              private store: Store,
              private dialogService: MatDialog) {
  }

  get noOrders(): boolean {
    return this.orders?.length === 0
  }

  ngOnInit(): void {
    this.orderService.all(userIdFromStore(this.store))
      .pipe(withLoading(this))
      .subscribe(
        orders => this.orders = orders,
        error => showError(this.dialogService, toMessage(error))
      )
  }

  openOrder(id: number) {
    this.store.dispatch(new Navigate([`/client/order/${id}`]))
  }
}
