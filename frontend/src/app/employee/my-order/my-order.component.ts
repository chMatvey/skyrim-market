import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from '@models/order/order';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderService } from '@services/order/order.service';
import { map, switchMap, tap } from 'rxjs/operators';
import { OrderStatusEnum } from '@models/order-status-enum';
import { withLoading } from '@utils/loading-util';
import { NotificationPopupComponent } from '@app/shared/notification-popup/notification-popup.component';
import { MatDialog } from '@angular/material/dialog';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-my-order',
  templateUrl: './my-order.component.html',
  styleUrls: ['./my-order.component.scss']
})
export class MyOrderComponent implements OnInit {

  order$: Observable<Order>

  loading: boolean

  form: FormGroup;

  private order: Order

  constructor(private activateRoute: ActivatedRoute,
              private orderService: OrderService,
              private router: Router,
              private dialogService: MatDialog) {
  }

  ngOnInit(): void {
    this.order$ = this.activateRoute.params
      .pipe(
        map(params => params['id']),
        switchMap(id => this.orderService.get(id)),
        tap(order => this.order = order)
      )

    this.form = new FormGroup({
      droppoint: new FormControl(null, [Validators.required])
    })
  }

  close() {
    this.router.navigate(['/employee/my-orders'])
  }

  decline() {
    this.orderService.update({...this.order, status: OrderStatusEnum.DECLINED})
      .pipe(
        withLoading(this),
        tap(() => this.showNotification('Order successfully declined!'))
      )
      .subscribe(() => this.router.navigate(['/employee/my-orders']))
  }

  complete() {
    this.orderService.update({...this.order, ...this.form.value, status: OrderStatusEnum.COMPLETED})
      .pipe(
        withLoading(this),
        tap(() => this.showNotification('Order status successfully changed to Completed!'))
      )
      .subscribe(() => this.router.navigate(['/employee/my-orders']))
  }

  private showNotification(data: string) {
    this.dialogService.open(NotificationPopupComponent, {
      data,
      panelClass: 'skyrim-popup'
    })
  }
}
