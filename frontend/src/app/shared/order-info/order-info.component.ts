import { Component, Input } from '@angular/core';
import { OrderTypeString } from '@models/order-type-string';
import { orderTypeToString } from '@utils/order-util';

@Component({
  selector: 'app-order-info',
  templateUrl: './order-info.component.html',
  styleUrls: ['./order-info.component.scss']
})
export class OrderInfoComponent {

  @Input()
  order: any

  get loading(): boolean {
    return !this.order
  }

  get itemLabel(): string {
    return this.order.type === 'FORGERY' ? 'What we must throw' : 'What we must steal?'
  }

  orderTypeToString(type: OrderTypeString) {
    return orderTypeToString(type)
  }
}
