import { Component, OnInit } from '@angular/core';
import { getOrderTypes } from '@utils/order';
import { Select } from '@models/select';
import { Store } from '@ngxs/store';
import { Navigate } from '@ngxs/router-plugin';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {

  orderTypes: Select<string>[] = getOrderTypes()

  constructor(private store: Store) {
  }

  ngOnInit(): void {
  }

  onOrderTypeChange(value: string) {
    this.store.dispatch([new Navigate([`/client/order/${value}`])])
  }
}
