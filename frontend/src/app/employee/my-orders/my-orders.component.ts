import { Component, OnInit } from '@angular/core';
import { Order } from '@models/order/order';
import { withLoading } from '@utils/loading-util';
import { Store } from '@ngxs/store'
import { MatDialog } from '@angular/material/dialog'
import { EmployeeOrderService } from '@services/order/employee-order.service'
import { userIdFromStore } from '@utils/store-util'
import { showError } from '@utils/notification-util'
import { toMessage } from '@utils/http-util'
import { Navigate } from '@ngxs/router-plugin'

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.scss']
})
export class MyOrdersComponent implements OnInit {
  orders: Order[]

  loading: boolean

  constructor(private orderService: EmployeeOrderService,
              private store: Store,
              private dialogService: MatDialog) {
  }

  ngOnInit(): void {
    this.orderService.all(userIdFromStore(this.store)).pipe(withLoading(this))
      .subscribe(
        orders => this.orders = orders,
        error => showError(this.dialogService, toMessage(error))
      )
  }

  openOrder(id: number) {
    this.store.dispatch(new Navigate([`/employee/my-order/${id}`]))
  }
}
