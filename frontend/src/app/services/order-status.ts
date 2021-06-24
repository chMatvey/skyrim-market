import { OrderStatus } from '@models/order-status';

export function orderStatusToString(status: OrderStatus): string {
  switch (status) {
    case OrderStatus.CREATED:
      return 'On approval with guild master'
    case OrderStatus.NEED_CHANGES:
      return 'Changes needed'
    case OrderStatus.APPROVED:
      return 'Paying'
    case OrderStatus.PAYED:
      return 'Being executed'
    case OrderStatus.DECLINED:
      return 'Declined'
    default:
      throw new Error('Unsupported order status')
  }
}
