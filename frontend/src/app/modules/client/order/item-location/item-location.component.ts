import { Component } from '@angular/core';
import { OrderStateService } from '@services/order-state.service';

@Component({
  selector: 'app-item-location',
  templateUrl: './item-location.component.html',
  styleUrls: ['./item-location.component.scss']
})
export class ItemLocationComponent {

  constructor(private orderStateService: OrderStateService) {
  }

  get place() {
    return this.orderStateService.order.droppoint
  }
}
