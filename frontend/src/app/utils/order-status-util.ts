import { OrderStatusEnum } from '@models/order-status-enum';

export function orderStatusToString(status: OrderStatusEnum): string {
  switch (status) {
    case OrderStatusEnum.CREATED:
      return 'On approval with guild master'
    case OrderStatusEnum.NEED_CHANGES:
      return 'Changes needed'
    case OrderStatusEnum.APPROVED:
      return 'Paying'
    case OrderStatusEnum.PAYED:
      return 'Being executed'
    case OrderStatusEnum.DECLINED:
      return 'Declined'
    case OrderStatusEnum.IN_PROGRESS:
      return 'In Progress'
    case OrderStatusEnum.COMPLETED:
      return 'Completed'
    default:
      throw new Error('Unsupported order status')
  }
}
