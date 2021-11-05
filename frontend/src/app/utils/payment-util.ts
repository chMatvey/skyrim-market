import { Dropdown } from '@models/template/dropdown';
import { PaymentString } from '@models/payment-string';

export function getPaymentTypes(): Dropdown<PaymentString>[] {
  return [
    {
      name: 'Cash',
      value: 'CASH'
    }
  ]
}
