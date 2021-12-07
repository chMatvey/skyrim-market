import { Component, OnInit } from '@angular/core';
import { Order } from '@models/order/order';
import { withLoading } from '@utils/loading-util';
import { ClientOrderService } from '@services/order/client-order.service'
import { Store } from '@ngxs/store'
import { userIdFromStore } from '@utils/store-util'
import { MatDialog } from '@angular/material/dialog'
import { showError } from '@utils/notification-util'
import { toMessage } from '@utils/http-util'
import { Navigate } from '@ngxs/router-plugin'
import { FormControl } from '@angular/forms'
import { OrderStatusEnum } from '@models/order-status-enum'
import { BaseComponent } from '@app/shared/base/base.component'
import { takeUntil } from 'rxjs/operators'
import { filterByStatusFunction, StatusFilter } from '@utils/filter-util'

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent extends BaseComponent implements OnInit {
  orders: Order[]
  filteredOrders: Order[]

  loading: boolean

  filterControl = new FormControl('ALL')

  constructor(private orderService: ClientOrderService,
              private store: Store,
              private dialogService: MatDialog) {
    super()
  }

  get noOrders(): boolean {
    return this.orders?.length === 0
  }

  ngOnInit(): void {
    this.orderService.all(userIdFromStore(this.store))
      .pipe(withLoading(this))
      .subscribe(
        orders => {
          this.orders = orders
          this.filteredOrders = [...orders]
        },
        error => showError(this.dialogService, toMessage(error))
      )

    this.filterControl.valueChanges.pipe(takeUntil(this.ngUnsubscribe))
        .subscribe((value: StatusFilter | StatusFilter[]) => {
          const filterFunc = filterByStatusFunction(value)
          this.filteredOrders = this.orders.filter(filterFunc)
        })
  }

  openOrder(id: number) {
    this.store.dispatch(new Navigate([`/client/order/${id}`]))
  }
}
