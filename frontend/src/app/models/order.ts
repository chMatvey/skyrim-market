import { OrderStatus } from '@models/order-status';
import { User } from '@models/user';
import { OrderType } from '@models/order-type';
import { Payment } from '@models/payment';

export interface Order {
  id?: number,
  type?: OrderType,
  person?: string,
  title?: string,
  item?: string
  description?: string
  status?: OrderStatus,
  client?: User,
  address?: string,
  date?: string,
  payment?: Payment,
  price?: number,
  contractor?: number
}
