import { Component, OnInit } from '@angular/core';
import { map, switchMap, tap } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderService } from '@services/order.service';
import { Order } from '@models/order/order';
import { UserService } from '@services/user.service';
import { withLoading } from '@utils/loading-util';
import { OrderStatusEnum } from '@models/order-status-enum';
import { MatDialog } from '@angular/material/dialog';
import { NotificationPopupComponent } from '@app/shared/notification-popup/notification-popup.component';

@Component({
  selector: 'app-available-order',
  templateUrl: './available-order.component.html',
  styleUrls: ['./available-order.component.scss']
})
export class AvailableOrderComponent implements OnInit {

  order$: Observable<Order>

  loading: boolean

  private order: Order

  constructor(private activateRoute: ActivatedRoute,
              private orderService: OrderService,
              private router: Router,
              private authService: UserService,
              private dialogService: MatDialog) {
  }

  get userId() {
    return this.authService.user.id
  }

  ngOnInit(): void {
    this.order$ = this.activateRoute.params
      .pipe(
        map(params => params['id']),
        switchMap(id => this.orderService.get(id)),
        tap(order => this.order = order)
      )
  }

  close() {
    this.router.navigate(['/employee/available-orders'])
  }

  assignToMe() {
    this.orderService.update({...this.order, contractor: this.userId, status: OrderStatusEnum.IN_PROGRESS})
      .pipe(
        withLoading(this),
        map(order => order.id),
        tap(() => this.showNotification('Order assigned to you!'))
      )
      .subscribe(id => this.router.navigate([`/employee/my-order/${id}`]))
  }

  private showNotification(data: string) {
    this.dialogService.open(NotificationPopupComponent, {
      data,
      panelClass: 'skyrim-popup'
    })
  }
}
