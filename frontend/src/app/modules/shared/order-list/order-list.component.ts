import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Order } from '@models/order';
import { OrderStatus } from '@models/order-status';
import { orderStatusToString } from '@services/order-status';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.scss']
})
export class OrderListComponent {

  @Input()
  orders: Order[]

  @Output()
  open = new EventEmitter<number>()

  orderStatus(status: OrderStatus) {
    return orderStatusToString(status)
  }

  openOrder(id: number) {
    this.open.emit(id)
  }
}
