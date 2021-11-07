import { Component, Input } from '@angular/core';
import { PickpocketingOrder } from '@models/order/pickpocketing-order'

@Component({
  selector: 'app-pickpocketing-order-info',
  templateUrl: './pickpocketing-order-info.component.html',
  styleUrls: ['../order-info.component.scss']
})
export class PickpocketingOrderInfoComponent {
  @Input()
  order: PickpocketingOrder
}
