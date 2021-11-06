import { PickpocketingOrder } from '@models/order/pickpocketing-order'
import { FormControl, FormGroup, Validators } from '@angular/forms'

export function createOrderFormForPickpocketing(order: PickpocketingOrder): FormGroup {
  return new FormGroup({
    person: new FormControl(order?.person, [Validators.required]),
    title: new FormControl(order?.title, [Validators.required]),
    item: new FormControl(order?.item, [Validators.required]),
    description: new FormControl(order?.description)
  })
}
