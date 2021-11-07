import { Order } from '@models/order/order'

export namespace Master {
  export class SetOrder {
    static readonly type = '[Master] set order'
    constructor(public order: Order) {}
  }
}
