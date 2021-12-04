import { Title } from '@models/title'
import { Item } from '@models/Item'
import { Order } from '@models/order/order'

export interface SweepOrder extends Order {
  address: string,
  title: Title,
  item: Item
  description?: string
}
