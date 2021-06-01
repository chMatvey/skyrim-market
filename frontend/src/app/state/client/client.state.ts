import { Order } from '@models/order';
import { Action, Selector, State, StateContext } from '@ngxs/store';
import { Injectable } from '@angular/core';
import { CreateOrder, SetOrder, SetOrderType } from '@state/client/client.actions';
import { OrderService } from '@services/order.service';
import { tap } from 'rxjs/operators';

export interface ClientStateModel {
  orderType: string
  orderId: number
  order: Order
  titles: string[]
}

const defaults: ClientStateModel = {
  orderType: null,
  orderId: null,
  order: null,
  titles: null
}

@State<ClientStateModel>({
  defaults,
  name: 'client'
})
@Injectable()
export class ClientState {

  constructor(private orderService: OrderService) {
  }

  @Selector()
  static orderType(state: ClientStateModel) {
    return state.orderType
  }

  @Selector()
  static orderId(state: ClientStateModel) {
    return state.orderId
  }

  @Selector()
  static orderStatus(state: ClientStateModel) {
    return state.order?.status
  }

  @Selector()
  static order(state: ClientStateModel) {
    return state.order
  }

  @Action(CreateOrder, {cancelUncompleted: true})
  createOrder({patchState}: StateContext<ClientStateModel>, {payload}: CreateOrder) {
    return this.orderService.create(payload)
      .pipe(
        tap(order => patchState({order}))
      )
  }

  @Action(SetOrder)
  setOrder({patchState}: StateContext<ClientStateModel>, {payload}: SetOrder) {
    patchState({order: payload})
  }

  @Action(SetOrderType)
  setOrderType({patchState}: StateContext<ClientStateModel>, {payload}: SetOrderType) {
    patchState({orderType: payload})
  }
}
