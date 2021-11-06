import { ForgeryOrder } from '@models/order/forgery-order'
import { FormControl, FormGroup, Validators } from '@angular/forms'

export function createOrderFormForForgery(order: ForgeryOrder): FormGroup {
  return new FormGroup({
    person: new FormControl(order?.person, [Validators.required]),
    address: new FormControl(order?.address, [Validators.required]),
    item: new FormControl(order?.item, [Validators.required]),
    description: new FormControl(order?.description)
  })
}
