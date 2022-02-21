import { Dropdown } from '@models/template/dropdown'
import { OrderTypeString } from '@models/order-type-string'

export function getOrderTypes(): Dropdown<OrderTypeString>[] {
  return [
    {
      name: 'Кража',
      value: 'PICKPOCKETING'
    },
    {
      name: 'Очистка',
      value: 'SWEEP'
    },
    {
      name: 'Подлог',
      value: 'FORGERY'
    }
  ]
}

export function orderTypeToString(status: OrderTypeString): string {
  switch (status) {
    case 'PICKPOCKETING':
      return 'Кража'
    case 'SWEEP':
      return 'Очистка'
    case 'FORGERY':
      return 'Подлог'
    default:
      throw new Error('Тип заказа не поддерживается')
  }
}
