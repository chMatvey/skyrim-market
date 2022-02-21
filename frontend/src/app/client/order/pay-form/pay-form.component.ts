import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Order } from '@models/order/order';
import { withLoading } from '@utils/loading-util';
import { MatDialog } from '@angular/material/dialog';
import { take, takeUntil } from 'rxjs/operators';
import { Select, Store } from '@ngxs/store'
import { ClientState } from '@state/client/client.state'
import { Observable } from 'rxjs'
import { Client } from '@state/client/client.actions'
import { showError, showNotification } from '@utils/notification-util'
import { toMessage } from '@utils/http-util'
import { BaseComponent } from '@app/shared/base/base.component'
import { PaymentService } from '@services/payment.service'
import { Payment } from '@models/payment'
import PayOrder = Client.PayOrder
import { paymentTypeToString } from "@utils/payment-type-util";

@Component({
  selector: 'app-pay-form',
  templateUrl: './pay-form.component.html',
  styleUrls: ['./pay-form.component.scss']
})
export class PayFormComponent extends BaseComponent implements OnInit {
  @Select(ClientState.order)
  order$: Observable<Order>

  form: FormGroup

  payments: Payment[] = []

  loading = false

  private order: Order

  constructor(private store: Store,
              private dialogService: MatDialog,
              private paymentService: PaymentService) {
    super()
  }

  get price(): number {
    return this.order.price
  }

  paymentString(payment: string): string {
    return paymentTypeToString(payment)
  }

  ngOnInit(): void {
    this.order$
      .pipe(
        takeUntil(this.ngUnsubscribe),
        take(1),
        withLoading(this)
      )
      .subscribe(
        order => this.initOrderForm(order),
        error => showError(this.dialogService, toMessage(error))
      )

    this.paymentService.all().subscribe(payments => this.payments = payments)
  }

  apply() {
    this.store.dispatch(new PayOrder(this.order.id, this.form.value.payment))
      .pipe(withLoading(this))
      .subscribe(
        () => showNotification(this.dialogService, 'Заказ успешно оплачен'),
        error => showError(this.dialogService, toMessage(error))
      )
  }

  private initOrderForm(order: Order): void {
    this.order = order
    this.form = new FormGroup({
      payment: new FormControl(order.payment, [Validators.required])
    })
  }
}
