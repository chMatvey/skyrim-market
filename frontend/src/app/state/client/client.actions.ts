import { Order } from '@models/order';

export class CreateOrder {
  static readonly type = '[client] create order'
  constructor(public payload: Order) {}
}

export class SetOrder {
  static readonly type = '[client] set order'
  constructor(public payload: Order) {}
}

export class SetOrderType {
  static readonly type = '[client] set order type'
  constructor(public payload: string) {}
}
