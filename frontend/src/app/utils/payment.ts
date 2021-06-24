import { Dropdown } from '@models/dropdown';
import { Payment } from '@models/payment';

export function getPaymentTypes(): Dropdown<Payment>[] {
  return [
    {
      name: 'Cash',
      value: 'CASH'
    }
  ]
}
