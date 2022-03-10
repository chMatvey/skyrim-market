import { Component, OnInit } from '@angular/core';
import { Order } from '@models/order/order'
import { userIdFromStore } from '@utils/store-util'
import { withLoading } from '@utils/loading-util'
import { showError } from '@utils/notification-util'
import { toMessage } from '@utils/http-util'
import { Navigate } from '@ngxs/router-plugin'
import { EmployeeOrderService } from '@services/order/employee-order.service'
import { Store } from '@ngxs/store'
import { MatDialog } from '@angular/material/dialog'

@Component({
  selector: 'app-completed-orders',
  templateUrl: './completed-orders.component.html',
  styleUrls: ['./completed-orders.component.scss']
})
export class CompletedOrdersComponent implements OnInit {
  orders: Order[]

  loading = true

  constructor(private orderService: EmployeeOrderService,
              private store: Store,
              private dialogService: MatDialog) {
  }

  ngOnInit(): void {
    this.orderService.completed().pipe(withLoading(this))
      .subscribe(
        orders => this.orders = orders,
        error => showError(this.dialogService, toMessage(error))
      )
  }

  openOrder(id: number) {
    this.store.dispatch(new Navigate([`/employee/completed-order/${id}`]))
  }
}
