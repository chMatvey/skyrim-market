import { Order } from '@models/order/order'
import { Item } from '@models/Item'
import { Title } from '@models/title'

export interface PickpocketingOrder extends Order {
  person: string,
  title: Title,
  item: Item
  description?: string
}
