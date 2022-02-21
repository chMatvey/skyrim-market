import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from '@models/order/order';
import { ActivatedRoute } from '@angular/router';
import { OrderService } from '@services/order.service';
import { filter, map, switchMap, tap } from 'rxjs/operators';
import { withLoading } from '@utils/loading-util';
import { MatDialog } from '@angular/material/dialog';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Store } from '@ngxs/store'
import { Navigate } from '@ngxs/router-plugin'
import { EmployeeOrderService } from '@services/order/employee-order.service'
import { showNotification } from '@utils/notification-util'

@Component({
  selector: 'app-my-order',
  templateUrl: './my-order.component.html',
  styleUrls: ['./my-order.component.scss']
})
export class MyOrderComponent implements OnInit {
  order$: Observable<Order>
  orderForm: FormGroup;
  private order: Order

  loading: boolean

  constructor(private activateRoute: ActivatedRoute,
              private orderService: OrderService,
              private employeeOrderService: EmployeeOrderService,
              private store: Store,
              private dialogService: MatDialog) {
  }

  get declineDisabled(): boolean {
    return this.orderForm.get('comment').invalid
  }

  get completeDisabled(): boolean {
    return this.orderForm.get('droppoint').invalid
  }

  ngOnInit(): void {
    this.order$ = this.activateRoute.params
      .pipe(
        map(params => params['id']),
        filter(id => !!id),
        switchMap(id => this.orderService.get(id)),
        tap(order => this.order = order)
      )

    this.orderForm = new FormGroup({
      droppoint: new FormControl(null, [Validators.required]),
      comment: new FormControl(null, [Validators.required])
    })
  }

  close() {
    this.store.dispatch(new Navigate(['/student/my-orders']))
  }

  decline() {
    this.employeeOrderService.decline(this.order.id, this.orderForm.value)
      .pipe(
        withLoading(this),
        tap(() => showNotification(this.dialogService, 'Заказ успешно отменен!'))
      )
      .subscribe(() => this.store.dispatch(new Navigate(['/student/my-orders'])))
  }

  complete() {
    this.employeeOrderService.complete(this.order.id, this.orderForm.value)
      .pipe(
        withLoading(this),
        tap(() => showNotification(this.dialogService, 'Заказ выполнен!'))
      )
      .subscribe(() => this.store.dispatch(new Navigate(['/student/my-orders'])))
  }
}
