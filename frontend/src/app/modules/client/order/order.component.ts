import { Component, OnInit } from '@angular/core';
import { getOrderTypes } from '@utils/order';
import { Dropdown } from '@models/dropdown';
import { BaseComponent } from '@shared/base/base.component';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderStateService } from '@services/order-state.service';
import { OrderType } from '@models/order-type';
import { Order } from '@models/order';
import { OrderStatus } from '@models/order-status';
import { orderStatusToString } from '@services/order-status';
import { map, switchMap } from 'rxjs/operators';
import { of } from 'rxjs';
import { OrderService } from '@services/order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent extends BaseComponent implements OnInit {

  orderTypes: Dropdown<string>[] = getOrderTypes()

  constructor(private router: Router,
              private orderStateService: OrderStateService,
              private activateRoute: ActivatedRoute,
              private orderService: OrderService) {
    super()
  }

  get order(): Order {
    return this.orderStateService.order
  }

  get orderType(): OrderType {
    return this.order?.type
  }

  get showPayForm(): boolean {
    return this.order?.status === OrderStatus.APPROVED || true
  }

  get orderStatus(): string {
    return orderStatusToString(this.order.status)
  }

  ngOnInit(): void {
    this.activateRoute.params
      .pipe(
        map(params => params['id']),
        switchMap(id => id ? this.orderService.get(id) : of(null)),
      )
      .subscribe(
        order => this.orderStateService.order = order,
        error => console.log(error)
      )
  }

  onOrderTypeChange(type: OrderType) {
    this.orderStateService.order = {...this.order, type}
  }
}
