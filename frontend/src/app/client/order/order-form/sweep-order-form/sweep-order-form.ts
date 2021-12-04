import { SweepOrder } from '@models/order/sweep-order'
import { FormControl, FormGroup, Validators } from '@angular/forms'

export function createOrderFormForSweep(order: SweepOrder): FormGroup {
  return new FormGroup({
    address: new FormControl(order?.address, [Validators.required]),
    title: new FormControl(order?.title, [Validators.required]),
    item: new FormControl(order?.item, [Validators.required]),
    description: new FormControl(order?.description)
  })
}
