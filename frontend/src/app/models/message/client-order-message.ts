import { OrderStatusEnum } from '@models/order-status-enum'

export interface ClientOrderMessage {
  orderId: number,
  orderStatus: OrderStatusEnum
}
