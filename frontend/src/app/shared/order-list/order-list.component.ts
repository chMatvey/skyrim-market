import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Order } from '@models/order/order';

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

  orderStatus(order: Order): string {
    return order.status.name
  }

  description(order: Order): string {
    return (order as any)?.description
  }

  openOrder(id: number): void {
    this.open.emit(id)
  }
}
