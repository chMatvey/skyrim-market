import { Dropdown } from '@models/dropdown';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Order } from '@models/order';
import { OrderStatus } from '@models/order-status';

export function getOrderTypes(): Dropdown<string>[] {
  return [
    {
      name: 'Pickpocketing',
      value: 'pickpocketing'
    }
  ]
}

export function createOrderForm(order: Order): FormGroup {
  return new FormGroup({

    person: new FormControl(order?.person, [Validators.required]),
    title: new FormControl(order?.title, [Validators.required]),
    item: new FormControl(order?.item, [Validators.required]),
    description: new FormControl(order?.description)
  })
}

export function isNotEditableStatus(status: OrderStatus): boolean {
  const notEditableStatus = [OrderStatus.CREATED, OrderStatus.APPROVED, OrderStatus.PAYED]

  return !!notEditableStatus.find(s => s === status)
}

export function isEditableStatus(status: OrderStatus) {
  return !isNotEditableStatus(status)
}
