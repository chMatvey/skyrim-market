import { Pipe, PipeTransform } from '@angular/core';
import { OrderTypeString } from '@models/order-type-string'

@Pipe({
  name: 'orderType'
})
export class OrderTypePipe implements PipeTransform {
  transform(value: OrderTypeString): string {
    switch (value) {
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
}
