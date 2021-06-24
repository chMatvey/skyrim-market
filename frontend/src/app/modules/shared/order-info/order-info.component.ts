import { Component, Input } from '@angular/core';
import { Order } from '@models/order';
import { OrderType } from '@models/order-type';
import { orderTypeToString } from '@utils/order';

@Component({
  selector: 'app-order-info',
  templateUrl: './order-info.component.html',
  styleUrls: ['./order-info.component.scss']
})
export class OrderInfoComponent {

  @Input()
  order: Order

  get loading(): boolean {
    return !this.order
  }

  get itemLabel(): string {
    return this.order.type === 'FORGERY' ? 'What we must throw' : 'What we must steal?'
  }

  orderTypeToString(type: OrderType) {
    return orderTypeToString(type)
  }
}
