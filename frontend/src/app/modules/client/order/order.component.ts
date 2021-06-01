import { Component, OnInit } from '@angular/core';
import { getOrderTypes } from '@utils/order';
import { Select, Store } from '@ngxs/store';
import { Navigate } from '@ngxs/router-plugin';
import { SetOrderType } from '@state/client/client.actions';
import { Observable } from 'rxjs';
import { ClientState } from '@state/client/client.state';
import { Dropdown } from '@models/dropdown';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {

  orderTypes: Dropdown<string>[] = getOrderTypes()

  @Select(ClientState.orderType)
  orderType$: Observable<string>

  constructor(private store: Store) {
  }

  ngOnInit(): void {
  }

  onOrderTypeChange(value: string) {
    this.store.dispatch([
      new SetOrderType(value),
      new Navigate([`/client/order/${value}`])
    ])
  }
}
