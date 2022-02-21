import { PaymentString } from "@models/payment-string";


export function paymentTypeToString(status: string): string {
  switch (status) {
    case 'Cash':
      return 'Наличные'
    case 'Bank':
      return 'Банк'
    default:
      throw new Error('Тип заказа не поддерживается')
  }
}
