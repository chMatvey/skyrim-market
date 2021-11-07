import { Component, Input } from '@angular/core';
import { OrderTypeString } from '@models/order-type-string';
import { Order } from '@models/order/order'
import { PickpocketingOrder } from '@models/order/pickpocketing-order'
import { SweepOrder } from '@models/order/sweep-order'
import { ForgeryOrder } from '@models/order/forgery-order'

@Component({
  selector: 'app-order-info',
  templateUrl: './order-info.component.html',
  styleUrls: ['./order-info.component.scss']
})
export class OrderInfoComponent {

  @Input()
  order: Order

  get orderType(): OrderTypeString {
    return this.order.type
  }

  get pickpocketingOrder(): PickpocketingOrder {
    return this.order as PickpocketingOrder
  }

  get sweepOrder(): SweepOrder {
    return this.order as SweepOrder
  }

  get forgeryOrder(): ForgeryOrder {
    return this.order as ForgeryOrder
  }
}
