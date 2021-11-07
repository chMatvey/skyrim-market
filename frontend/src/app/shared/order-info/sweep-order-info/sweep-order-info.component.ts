import { Component, Input } from '@angular/core';
import { SweepOrder } from '@models/order/sweep-order'

@Component({
  selector: 'app-sweep-order-info',
  templateUrl: './sweep-order-info.component.html',
  styleUrls: ['../order-info.component.scss']
})
export class SweepOrderInfoComponent {
  @Input()
  order: SweepOrder
}
