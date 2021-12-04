import { Pipe, PipeTransform } from '@angular/core';
import { OrderTypeString } from '@models/order-type-string'

@Pipe({
  name: 'orderType'
})
export class OrderTypePipe implements PipeTransform {
  transform(value: OrderTypeString): string {
    switch (value) {
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
}
