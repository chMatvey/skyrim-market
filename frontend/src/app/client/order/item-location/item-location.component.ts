import { Component } from '@angular/core';
import { Select } from '@ngxs/store'
import { ClientState } from '@state/client/client.state'
import { Observable } from 'rxjs'
import { Order } from '@models/order/order'

@Component({
  selector: 'app-item-location',
  templateUrl: './item-location.component.html',
  styleUrls: ['./item-location.component.scss']
})
export class ItemLocationComponent {
  @Select(ClientState.order)
  order$: Observable<Order>
}
