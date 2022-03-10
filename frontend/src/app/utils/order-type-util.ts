import { Dropdown } from '@models/template/dropdown'
import { OrderTypeString } from '@models/order-type-string'

export function getOrderTypes(): Dropdown<OrderTypeString>[] {
  return [
    {
      name: 'Карманная кража',
      value: 'PICKPOCKETING'
    },
    {
      name: 'Кража со взломом',
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
      return 'Карманная кража'
    case 'SWEEP':
      return 'Кража со взломом'
    case 'FORGERY':
      return 'Подлог'
    default:
      throw new Error('Тип заказа не поддерживается')
  }
}
