import { Dropdown } from '@models/template/dropdown'
import { OrderTypeString } from '@models/order-type-string'

export function getOrderTypes(): Dropdown<OrderTypeString>[] {
  return [
    {
      name: 'Pickpocketing',
      value: 'PICKPOCKETING'
    },
    {
      name: 'Sweep',
      value: 'SWEEP'
    },
    {
      name: 'Forgery',
      value: 'FORGERY'
    }
  ]
}

export function orderTypeToString(type: OrderTypeString): string {
  switch (type) {
    case 'PICKPOCKETING':
      return 'Pickpocketing'
    case 'SWEEP':
      return 'Sweep'
    case 'FORGERY':
      return 'Forgery'
    default:
      throw new Error('Unsupported order type')
  }
}
