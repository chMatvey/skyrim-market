import { Component, OnInit } from '@angular/core';
import { getOrderTypes, isNotEditableStatus } from '@utils/order';
import { Select, Store } from '@ngxs/store';
import { Navigate } from '@ngxs/router-plugin';
import { SetOrderType } from '@state/client/client.actions';
import { Observable } from 'rxjs';
import { ClientState } from '@state/client/client.state';
import { Dropdown } from '@models/dropdown';
import { map, takeUntil } from 'rxjs/operators';
import { BaseComponent } from '@shared/base/base.component';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent extends BaseComponent implements OnInit {

  orderTypes: Dropdown<string>[] = getOrderTypes()

  @Select(ClientState.orderType)
  orderType$: Observable<string>

  disabled$: Observable<boolean>

  constructor(private store: Store) {
    super()
  }

  ngOnInit(): void {
    this.disabled$ = this.store.select(ClientState.orderStatus)
      .pipe(
        takeUntil(this.ngUnsubscribe),
        map(status => isNotEditableStatus(status))
      )
  }

  onOrderTypeChange(value: string) {
    this.store.dispatch([
      new SetOrderType(value),
      new Navigate([`/client/order/${value}`])
    ])
  }
}
