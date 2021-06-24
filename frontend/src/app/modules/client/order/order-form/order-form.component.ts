import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable, ReplaySubject } from 'rxjs';
import { createOrderForm, disabledStatuses } from '@utils/order';
import { OrderStatus } from '@models/order-status';
import { tap } from 'rxjs/operators';
import { TitleService } from '@services/title.service';
import { BaseComponent } from '@shared/base/base.component';
import { OrderService } from '@services/order.service';
import { OrderStateService } from '@services/order-state.service';
import { Order } from '@models/order';
import { OrderType } from '@models/order-type';
import { MatDialog } from '@angular/material/dialog';
import { NotificationPopupComponent } from '@shared/notification-popup/notification-popup.component';
import { Router } from '@angular/router';
import { AuthService } from '@services/auth.service';

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.scss']
})
export class OrderFormComponent extends BaseComponent implements OnInit {

  form: FormGroup

  titles$: Observable<string[]>;

  orderType$ = new ReplaySubject<OrderType>(1)

  loading = false

  get userId() {
    return this.authService.user.id
  }

  constructor(private titleService: TitleService,
              private orderService: OrderService,
              private orderStateService: OrderStateService,
              private dialogService: MatDialog,
              private router: Router,
              private authService: AuthService) {
    super()
  }

  get orderType(): OrderType {
    return this.order?.type
  }

  @Input()
  set orderType(type: OrderType) {
    this.orderType$.next(type)
  }

  get order(): Order {
    return this.orderStateService.order
  }

  get orderStatus() {
    return this.order?.status
  }

  get formDisabled(): boolean {
    return disabledStatuses.includes(this.orderStatus)
  }

  get itemLabel(): string {
    return this.orderType === 'FORGERY' ? 'What we must throw' : 'What we must steal?'
  }

  ngOnInit(): void {
    this.titles$ = this.titleService.all()

    this.orderType$
      .subscribe(() => {
        this.form = createOrderForm(this.order)
        if (this.formDisabled) {
          this.form.disable()
        }
      })
  }

  onCancel() {
    const status = this.orderStateService.order.status
    this.orderStateService.order = null
    if (status) {
      this.router.navigate(['/client/order'])
    }
  }

  onCreateOrder() {
    this.orderService.create({...this.form.value, type: this.orderType, client: this.userId})
      .pipe(
        tap(() => this.showNotification('Order successfully created!'))
      )
      .subscribe(
        order => {
          this.orderStateService.order = null
          this.router.navigate([`/client/order/${order.id}`])
        },
        error => console.log(error)
        )
  }

  onUpdateOrder() {
    const order = {
      ...this.form.value,
      type: this.orderType,
      status: OrderStatus.CREATED,
      client: this.userId
    }
    this.orderService.create(order)
      .pipe(
        tap(() => this.showNotification('Order successfully updated!'))
      )
      .subscribe(
        order => this.orderStateService.order = order,
        error => console.log(error)
      )
  }

  onDeclineOrder() {
    this.orderService.update({...this.order, status: OrderStatus.DECLINED})
      .pipe(
        tap(() => this.showNotification('Order successfully declined!'))
      )
      .subscribe(
        () => {
          this.orderStateService.order = null
          this.router.navigate(['/client/order'])
        },
        error => console.log(error)
      )
  }

  private showNotification(data: string) {
    this.dialogService.open(NotificationPopupComponent, {
      data,
      panelClass: 'skyrim-popup'
    })
  }
}
