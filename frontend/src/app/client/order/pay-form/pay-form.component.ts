import { Component, OnInit } from '@angular/core';
import { Dropdown } from '@models/template/dropdown';
import { getPaymentTypes } from '@utils/payment-util';
import { PaymentString } from '@models/payment-string';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Order } from '@models/order/order';
import { withLoading } from '@utils/stream-pipe-operators';
import { MatDialog } from '@angular/material/dialog';
import { take } from 'rxjs/operators';
import { Select, Store } from '@ngxs/store'
import { ClientState } from '@state/client/client.state'
import { Observable } from 'rxjs'
import { Client } from '@state/client/client.actions'
import { showNotification } from '@utils/notification-util'
import PayOrder = Client.PayOrder

@Component({
  selector: 'app-pay-form',
  templateUrl: './pay-form.component.html',
  styleUrls: ['./pay-form.component.scss']
})
export class PayFormComponent implements OnInit {
  @Select(ClientState.order)
  order$: Observable<Order>

  form: FormGroup

  payments: Dropdown<PaymentString>[] = getPaymentTypes()

  loading = false

  private order: Order

  constructor(private store: Store,
              private dialogService: MatDialog) {
  }

  get price(): number {
    return this.order.price
  }

  ngOnInit(): void {
    this.order$
      .pipe(
        take(1),
        withLoading(this)
      )
      .subscribe(order => this.initOrderForm(order))
  }

  apply() {
    this.store
      .dispatch(new PayOrder(this.order.id, this.form.value))
      .pipe(withLoading(this))
      .subscribe(() => showNotification(this.dialogService, 'Order successfully payed'))
  }

  private initOrderForm(order: Order): void {
    this.order = order
    this.form = new FormGroup({
      payment: new FormControl(order.payment, [Validators.required])
    })
  }
}
