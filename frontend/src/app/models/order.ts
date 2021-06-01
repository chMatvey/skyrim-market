import { OrderStatus } from '@models/order-status';
import { User } from '@models/user';

export interface Order {
  type: string,
  person: string,
  title: string,
  item: string
  description: string
  status: OrderStatus,
  client: User
}
