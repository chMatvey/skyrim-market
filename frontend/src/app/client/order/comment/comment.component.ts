import { Component } from '@angular/core';
import { Select } from '@ngxs/store'
import { ClientState } from '@state/client/client.state'
import { Observable } from 'rxjs'
import { Order } from '@models/order/order'

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.scss']
})
export class CommentComponent {
  @Select(ClientState.order)
  order$: Observable<Order>
}
