import { Order } from '@models/order/order'
import { Item } from '@models/Item'

export interface ForgeryOrder extends Order {
  person: string,
  address: string,
  item: Item
  description?: string
}
