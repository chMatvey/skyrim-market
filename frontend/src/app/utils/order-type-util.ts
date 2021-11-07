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
