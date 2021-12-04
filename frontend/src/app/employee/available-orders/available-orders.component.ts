import { Component, OnInit } from '@angular/core';
import { Order } from '@models/order/order';
import { withLoading } from '@utils/loading-util';
import { EmployeeOrderService } from '@services/order/employee-order.service'
import { Store } from '@ngxs/store'
import { MatDialog } from '@angular/material/dialog'
import { showError } from '@utils/notification-util'
import { toMessage } from '@utils/http-util'
import { Navigate } from '@ngxs/router-plugin'

@Component({
  selector: 'app-available-orders',
  templateUrl: './available-orders.component.html',
  styleUrls: ['./available-orders.component.scss']
})
export class AvailableOrdersComponent implements OnInit {

  orders: Order[]

  loading: boolean

  constructor(private orderService: EmployeeOrderService,
              private store: Store,
              private dialogService: MatDialog) {
  }

  ngOnInit(): void {
    this.orderService.payed().pipe(withLoading(this))
      .subscribe(
        orders => this.orders = orders,
        error => showError(this.dialogService, toMessage(error))
      )
  }

  openOrder(id: number) {
    this.store.dispatch(new Navigate([`/employee/available-order/${id}`]))
  }
}
