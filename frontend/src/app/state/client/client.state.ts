import { Order } from '@models/order/order';
import { Action, Selector, State, StateContext } from '@ngxs/store';
import { Injectable } from '@angular/core';
import { OrderService } from '@services/order.service';
import { tap } from 'rxjs/operators';
import { Client } from '@state/client/client.actions'
import { ClientOrderService } from '@services/order/client-order.service'
import { OrderTypeString } from '@models/order-type-string'
import { ClientOrderMessage } from '@models/message/client-order-message'
import { clientInitialState } from '@state/client/initial-state'
import { localStorageOrderMessagesField } from '@app/app.const'
import CreateOrder = Client.CreateOrder
import SetOrderType = Client.SetOrderType
import Reset = Client.Reset
import GetOrderById = Client.GetOrderById
import UpdateOrder = Client.UpdateOrder
import DeclineOrder = Client.DeclineOrder
import PayOrder = Client.PayOrder
import SetOrder = Client.SetOrder
import AddOrderMessage = Client.AddOrderMessage
import RemoveOrderMessage = Client.RemoveOrderMessage
import RemoveOrderMessagesById = Client.RemoveOrderMessagesById

export interface ClientStateModel<T extends Order = Order> {
  order: T,
  orderMessages: ClientOrderMessage[]
}


@State<ClientStateModel>({
  defaults: clientInitialState(),
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

  @Selector()
  static orderMessages(state: ClientStateModel): ClientOrderMessage[] {
    return state.orderMessages
  }

  @Action(SetOrder)
  setOrder({patchState}: StateContext<ClientStateModel>, {order}: CreateOrder) {
    patchState({order})
  }

  @Action(CreateOrder, {cancelUncompleted: true})
  createOrder({patchState}: StateContext<ClientStateModel>, {order}: CreateOrder) {
    return this.clientOrderService.create(order).pipe(
      tap(order => patchState({order}))
    )
  }

  @Action(UpdateOrder, {cancelUncompleted: true})
  updateOrder({patchState}: StateContext<ClientStateModel>, {order}: UpdateOrder) {
    return this.clientOrderService.update(order).pipe(
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
    return this.clientOrderService.decline(id).pipe(
      tap(order => patchState({order}))
    )
  }

  @Action(PayOrder)
  payOrder({patchState}: StateContext<ClientStateModel>, {id, payment}: PayOrder) {
    return this.clientOrderService.pay(id, payment).pipe(
      tap(order => patchState({order}))
    )
  }

  @Action(Reset)
  reset({setState}: StateContext<ClientStateModel>) {
    setState(clientInitialState())
  }

  @Action(AddOrderMessage)
  addOrderMessage({patchState, getState}: StateContext<ClientStateModel>, {message}: AddOrderMessage) {
    const prevState = getState()
    const orderMessages = [...prevState.orderMessages, message]
    patchState({orderMessages})
    localStorage.setItem(localStorageOrderMessagesField, JSON.stringify(orderMessages))
  }

  @Action(RemoveOrderMessage)
  removeOrderMessage({patchState, getState}: StateContext<ClientStateModel>, {message}: RemoveOrderMessage) {
    const prevState = getState()
    const orderMessages = prevState.orderMessages
      .filter(msg => msg.orderId !== message.orderId && msg.orderStatus !== message.orderStatus)
    patchState({orderMessages})
    localStorage.setItem(localStorageOrderMessagesField, JSON.stringify(orderMessages))
  }

  @Action(RemoveOrderMessagesById)
  removeOrderMessagesById({patchState, getState}: StateContext<ClientStateModel>, {orderId}: RemoveOrderMessagesById) {
    const prevState = getState()
    const orderMessages = prevState.orderMessages.filter(msg => msg.orderId !== orderId)
    patchState({orderMessages})
    localStorage.setItem(localStorageOrderMessagesField, JSON.stringify(orderMessages))
  }
}
