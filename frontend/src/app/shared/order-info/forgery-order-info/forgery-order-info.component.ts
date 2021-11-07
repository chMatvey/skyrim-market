import { Component, Input } from '@angular/core';
import { ForgeryOrder } from '@models/order/forgery-order'

@Component({
  selector: 'app-forgery-order-info',
  templateUrl: './forgery-order-info.component.html',
  styleUrls: ['../order-info.component.scss']
})
export class ForgeryOrderInfoComponent {
  @Input()
  order: ForgeryOrder
}
