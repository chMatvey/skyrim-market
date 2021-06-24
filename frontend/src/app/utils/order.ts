import { Dropdown } from '@models/dropdown';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Order } from '@models/order';
import { OrderStatus } from '@models/order-status';
import { OrderType } from '@models/order-type';

export function getOrderTypes(): Dropdown<OrderType>[] {
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

export function createOrderForm(order: Order): FormGroup {
  switch (order.type) {
    case 'PICKPOCKETING':
      return createOrderFormForPickpocketing(order)
    case 'SWEEP':
      return createOrderFormForSweep(order)
    case 'FORGERY':
      return createOrderFormForForgery(order)
    default:
      throw new Error('Unsupported order type')
  }
}

function createOrderFormForPickpocketing(order: Order): FormGroup {
  return new FormGroup({
    person: new FormControl(order?.person, [Validators.required]),
    title: new FormControl(order?.title, [Validators.required]),
    item: new FormControl(order?.item, [Validators.required]),
    description: new FormControl(order?.description)
  })
}

function createOrderFormForSweep(order: Order): FormGroup {
  return new FormGroup({
    address: new FormControl(order?.address, [Validators.required]),
    title: new FormControl(order?.title, [Validators.required]),
    item: new FormControl(order?.item, [Validators.required]),
    description: new FormControl(order?.description)
  })
}

function createOrderFormForForgery(order: Order): FormGroup {
  return new FormGroup({
    person: new FormControl(order?.person, [Validators.required]),
    address: new FormControl(order?.address, [Validators.required]),
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

export const disabledStatuses = [
  OrderStatus.CREATED, OrderStatus.DECLINED, OrderStatus.PAYED, OrderStatus.APPROVED
]

export function orderTypeToString(type: OrderType): string {
  switch (type) {
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
