import { OrderStatusEnum } from '@models/order-status-enum';

export function orderStatusToString(status: OrderStatusEnum): string {
  switch (status) {
    case OrderStatusEnum.CREATED:
      return 'На согласовании'
    case OrderStatusEnum.NEED_CHANGES:
      return 'Нужны изменения'
    case OrderStatusEnum.APPROVED:
      return 'Подтвержден'
    case OrderStatusEnum.PAYED:
      return 'Оплачен'
    case OrderStatusEnum.DECLINED:
      return 'Отменен'
    case OrderStatusEnum.IN_PROGRESS:
      return 'Выполняется'
    case OrderStatusEnum.COMPLETED:
      return 'Завершен'
    default:
      throw new Error('Неподдерживаемый статус заказа')
  }
}
