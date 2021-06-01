import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Select, Store } from '@ngxs/store';
import { ClientState } from '@state/client/client.state';
import { Observable } from 'rxjs';
import { createOrderForm } from '@utils/order';
import { CreateOrder, SetOrder } from '@state/client/client.actions';
import { OrderStatus } from '@models/order-status';
import { Navigate } from '@ngxs/router-plugin';
import { AppState } from '@state/app.state';
import { switchMap } from 'rxjs/operators';
import { TitleService } from '@services/title.service';

@Component({
  selector: 'app-pickpocketing-form',
  templateUrl: './pickpocketing-form.component.html',
  styleUrls: ['./pickpocketing-form.component.scss']
})
export class PickpocketingFormComponent implements OnInit {

  @Select(ClientState.orderStatus)
  orderStatus: Observable<OrderStatus>

  form: FormGroup

  titles$: Observable<string[]>;

  constructor(private store: Store,
              private titleService: TitleService) { }

  ngOnInit(): void {
    this.store.selectOnce(ClientState.order)
      .subscribe(order => this.form = createOrderForm(order))

    this.titles$ = this.titleService.all()
  }

  onCreateOrder() {
    this.store.selectOnce(AppState.user)
      .pipe(
        switchMap(client => this.store.dispatch([
          new CreateOrder({...this.form.value, client})
        ]))
      )
      .subscribe(order => this.store.dispatch([
        new SetOrder(order)
      ]))
  }

  onCancel() {
    this.store.dispatch([
      new Navigate(['/client'])
    ])
  }
}
