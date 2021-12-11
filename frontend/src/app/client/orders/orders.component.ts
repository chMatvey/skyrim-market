import { Component, OnInit } from '@angular/core';
import { Order } from '@models/order/order';
import { withLoading } from '@utils/loading-util';
import { ClientOrderService } from '@services/order/client-order.service'
import { Select, Store } from '@ngxs/store'
import { userIdFromStore } from '@utils/store-util'
import { MatDialog } from '@angular/material/dialog'
import { showError } from '@utils/notification-util'
import { toMessage } from '@utils/http-util'
import { Navigate } from '@ngxs/router-plugin'
import { FormControl } from '@angular/forms'
import { OrderStatusEnum } from '@models/order-status-enum'
import { BaseComponent } from '@app/shared/base/base.component'
import { map, takeUntil } from 'rxjs/operators'
import { filterByStatusFunction, StatusFilter } from '@utils/filter-util'
import { Observable } from 'rxjs'
import { ClientState } from '@state/client/client.state'
import { ClientOrderMessage } from '@models/message/client-order-message'
import { Client } from '@state/client/client.actions'
import COMPLETED = OrderStatusEnum.COMPLETED
import IN_PROGRESS = OrderStatusEnum.IN_PROGRESS
import APPROVED = OrderStatusEnum.APPROVED
import NEED_CHANGES = OrderStatusEnum.NEED_CHANGES
import DECLINED = OrderStatusEnum.DECLINED
import RemoveOrderMessagesById = Client.RemoveOrderMessagesById

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

  @Select(ClientState.orderMessages)
  orderMessages$: Observable<ClientOrderMessage[]>

  constructor(private orderService: ClientOrderService,
              private store: Store,
              private dialogService: MatDialog) {
    super()
  }

  get noOrders(): boolean {
    return this.orders?.length === 0
  }

  get completedCount$(): Observable<number> {
    return this.messagesCountByStatus(COMPLETED)
  }

  get inProgressCount$(): Observable<number> {
    return this.messagesCountByStatus(IN_PROGRESS)
  }

  get approvedCount$(): Observable<number> {
    return this.messagesCountByStatus(APPROVED)
  }

  get needChangesCount$(): Observable<number> {
    return this.messagesCountByStatus(NEED_CHANGES)
  }

  get declinedCount$(): Observable<number> {
    return this.messagesCountByStatus(DECLINED)
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
    this.store.dispatch([
      new RemoveOrderMessagesById(id),
      new Navigate([`/client/order/${id}`])
    ])
  }

  orderStatus(order: Order): string {
    return order.status.name
  }

  description(order: Order): string {
    return (order as any)?.description
  }

  private messagesCountByStatus(status: OrderStatusEnum): Observable<number> {
    return this.orderMessages$.pipe(
      map(messages => messages.filter(msg => msg.orderStatus === status).length)
    )
  }

  isOrderBudgeHidden(orders: ClientOrderMessage[], order: Order) {
    return orders.filter(filteringOrder => filteringOrder.orderId === order.id).length === 0
  }
}
