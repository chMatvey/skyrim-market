import { Component, OnInit } from '@angular/core';
import { Dropdown } from '@models/dropdown';
import { getPaymentTypes } from '@utils/payment';
import { Payment } from '@models/payment';
import { OrderStateService } from '@services/order-state.service';
import { OrderService } from '@services/order.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Order } from '@models/order';
import { OrderStatus } from '@models/order-status';
import { withLoading } from '@utils/stream-pipe-operators';
import { NotificationPopupComponent } from '@shared/notification-popup/notification-popup.component';
import { MatDialog } from '@angular/material/dialog';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-pay-form',
  templateUrl: './pay-form.component.html',
  styleUrls: ['./pay-form.component.scss']
})
export class PayFormComponent implements OnInit {

  form: FormGroup

  payments: Dropdown<Payment>[] = getPaymentTypes()

  loading: boolean

  constructor(private orderStateService: OrderStateService,
              private orderService: OrderService,
              private dialogService: MatDialog) { }

  get order(): Order {
    return this.orderStateService.order
  }

  get price(): number {
    return this.order.price
  }

  ngOnInit(): void {
    this.form = new FormGroup({
      payment: new FormControl(this.order.payment, [Validators.required])
    })
  }

  apply() {
    this.orderService.update({...this.order, ...this.form.value, status: OrderStatus.PAYED})
      .pipe(
        withLoading(this),
        tap(() => this.showNotification('Order successfully payed'))
      )
      .subscribe(order => this.orderStateService.order = order)
  }

  private showNotification(data: string) {
    this.dialogService.open(NotificationPopupComponent, {
      data,
      panelClass: 'skyrim-popup'
    })
  }
}
