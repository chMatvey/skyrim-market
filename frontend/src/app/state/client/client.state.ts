import { Order } from '@models/order/order';
import { Action, Selector, State, StateContext } from '@ngxs/store';
import { Injectable } from '@angular/core';
import { OrderService } from '@services/order/order.service';
import { tap } from 'rxjs/operators';
import { Client } from '@state/client/client.actions'
import { ClientOrderService } from '@services/order/client-order.service'
import { OrderTypeString } from '@models/order-type-string'
import CreateOrder = Client.CreateOrder
import SetOrderType = Client.SetOrderType
import Reset = Client.Reset
import GetOrderById = Client.GetOrderById
import UpdateOrder = Client.UpdateOrder
import DeclineOrder = Client.DeclineOrder
import PayOrder = Client.PayOrder

export interface ClientStateModel<T extends Order = Order> {
  order: T
}

const defaults: ClientStateModel = {
  order: {
    type: 'PICKPOCKETING',
  },
}

@State<ClientStateModel>({
  defaults,
  name: 'client'
})
@Injectable()
export class ClientState {
  constructor(private orderService: OrderService,
              private clientOrderService: ClientOrderService) {}

  @Selector()
  static order(state: ClientStateModel): Order | null {
    return state.order
  }

  @Selector()
  static orderType(state: ClientStateModel): OrderTypeString | null {
    return state.order?.type ?? null
  }

  @Action(CreateOrder, {cancelUncompleted: true})
  createOrder({patchState}: StateContext<ClientStateModel>, {order}: CreateOrder) {
    return this.orderService.create(order).pipe(
      tap(order => patchState({order}))
    )
  }

  @Action(UpdateOrder, {cancelUncompleted: true})
  updateOrder({patchState}: StateContext<ClientStateModel>, {order}: UpdateOrder) {
    return this.orderService.update(order).pipe(
      tap(order => patchState({order}))
    )
  }

  @Action(GetOrderById, {cancelUncompleted: true})
  getOrderById({patchState}: StateContext<ClientStateModel>, {id}: GetOrderById) {
    return this.orderService.get(id).pipe(
      tap(order => patchState({order}))
    )
  }

  @Action(SetOrderType)
  setOrderType({patchState, getState}: StateContext<ClientStateModel>, {type}: SetOrderType) {
    const prevState = getState()
    const order = {...prevState.order, type}
    patchState({order})
  }

  @Action(DeclineOrder)
  declineOrder({patchState}: StateContext<ClientStateModel>, {id}: DeclineOrder) {
    this.clientOrderService.decline(id).pipe(
      tap(order => patchState({order}))
    )
  }

  @Action(PayOrder)
  payOrder({patchState}: StateContext<ClientStateModel>, {id, payment}: PayOrder) {
    this.clientOrderService
  }

  @Action(Reset)
  reset({setState}: StateContext<ClientStateModel>) {
    setState({...defaults})
  }
}
