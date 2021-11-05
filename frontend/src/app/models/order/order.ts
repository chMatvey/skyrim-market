import { User } from '@models/user';
import { OrderTypeString } from '@models/order-type-string';
import { OrderStatus } from '@models/order-status'
import { Payment } from '@models/payment'

export interface Order {
  id?: number,
  price?: number,
  droppoint?: string
  comment?: string
  startDate?: Date,
  endDate?: Date,
  status?: OrderStatus,
  client?: User,
  contractor?: User,
  payment?: Payment
  type?: OrderTypeString,
}
