import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Select, Store } from '@ngxs/store';
import { ClientState } from '@state/client/client.state';
import { Observable } from 'rxjs';
import { createOrderForm, isNotEditableStatus } from '@utils/order';
import { CreateOrder } from '@state/client/client.actions';
import { OrderStatus } from '@models/order-status';
import { Navigate } from '@ngxs/router-plugin';
import { takeUntil } from 'rxjs/operators';
import { TitleService } from '@services/title.service';
import { BaseComponent } from '@shared/base/base.component';
import { userFromStore } from '@utils/user';

@Component({
  selector: 'app-pickpocketing-form',
  templateUrl: './pickpocketing-form.component.html',
  styleUrls: ['./pickpocketing-form.component.scss']
})
export class PickpocketingFormComponent extends BaseComponent implements OnInit {

  static readonly orderType = 'PICKPOCKETING'

  @Select(ClientState.orderStatus)
  orderStatus$: Observable<OrderStatus>

  form: FormGroup

  titles$: Observable<string[]>;

  constructor(private store: Store,
              private titleService: TitleService) {
    super()
  }

  ngOnInit(): void {
    this.store.selectOnce(ClientState.order)
      .subscribe(order => this.form = createOrderForm(order))

    this.orderStatus$
      .pipe(takeUntil(this.ngUnsubscribe))
      .subscribe(status => isNotEditableStatus(status) ? this.form.disable() : this.form.enable())

    this.titles$ = this.titleService.all()
  }

  onCreateOrder() {
    this.store.dispatch([
      new CreateOrder({
        ...this.form.value,
        client: userFromStore(this.store),
        type: PickpocketingFormComponent.orderType
      })
    ])
  }

  onCancel() {
    this.store.dispatch([
      new Navigate(['/client'])
    ])
  }

  onDeclineOrder() {
  }

  onUpdateOrder() {

  }
}
