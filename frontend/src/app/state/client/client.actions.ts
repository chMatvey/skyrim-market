import { Order } from '@models/order/order';
import { OrderTypeString } from '@models/order-type-string'
import { Payment } from '@models/payment'
import { ClientOrderMessage } from '@models/message/client-order-message'

export namespace Client {
  export class SetOrder {
    static readonly type = '[Client] set order'
    constructor(public order: Order) {}
  }

  export class CreateOrder {
    static readonly type = '[Client] create order'
    constructor(public order: Order) {}
  }

  export class UpdateOrder {
    static readonly type = '[Client] update order'
    constructor(public order: Order) {}
  }

  export class GetOrderById {
    static readonly type = '[Client] get order by id'
    constructor(public id: number) {}
  }

  export class SetOrderType {
    static readonly type = '[Client] set order type'
    constructor(public type: OrderTypeString) {}
  }

  export class DeclineOrder {
    static readonly type = '[Client] decline order'
    constructor(public id: number) {}
  }

  export class PayOrder {
    static readonly type = '[Client] pay order'
    constructor(public id: number, public payment: Payment) {}
  }

  export class Reset {
    static readonly type = '[Client] reset'
    constructor() {}
  }

  export class AddOrderMessage {
    static readonly type = '[Client] add order message'
    constructor(public message: ClientOrderMessage) {}
  }

  export class RemoveOrderMessage {
    static readonly type = '[Client] remove order message'
    constructor(public message: ClientOrderMessage) {}
  }

  export class RemoveOrderMessagesById {
    static readonly type = '[Client] remove order messages by id'
    constructor(public orderId: number) {}
  }
}
