import { Dropdown } from '@models/template/dropdown';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Order } from '@models/order/order';
import { OrderStatusEnum } from '@models/order-status-enum';
import { OrderTypeString } from '@models/order-type-string';
import { SweepOrder } from '@models/order/sweep-order'
import { PickpocketingOrder } from '@models/order/pickpocketing-order'
import { ForgeryOrder } from '@models/order/forgery-order'

export function getOrderTypes(): Dropdown<OrderTypeString>[] {
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
      return createOrderFormForPickpocketing(order as PickpocketingOrder)
    case 'SWEEP':
      return createOrderFormForSweep(order as SweepOrder)
    case 'FORGERY':
      return createOrderFormForForgery(order as ForgeryOrder)
    default:
      throw new Error('Unsupported order type')
  }
}

function createOrderFormForPickpocketing(order: PickpocketingOrder): FormGroup {
  return new FormGroup({
    person: new FormControl(order?.person, [Validators.required]),
    title: new FormControl(order?.title, [Validators.required]),
    item: new FormControl(order?.item, [Validators.required]),
    description: new FormControl(order?.description)
  })
}

function createOrderFormForSweep(order: SweepOrder): FormGroup {
  return new FormGroup({
    address: new FormControl(order?.address, [Validators.required]),
    title: new FormControl(order?.title, [Validators.required]),
    item: new FormControl(order?.item, [Validators.required]),
    description: new FormControl(order?.description)
  })
}

function createOrderFormForForgery(order: ForgeryOrder): FormGroup {
  return new FormGroup({
    person: new FormControl(order?.person, [Validators.required]),
    address: new FormControl(order?.address, [Validators.required]),
    item: new FormControl(order?.item, [Validators.required]),
    description: new FormControl(order?.description)
  })
}

export function isNotEditableStatus(status: OrderStatusEnum): boolean {
  const notEditableStatus = [OrderStatusEnum.CREATED, OrderStatusEnum.APPROVED, OrderStatusEnum.PAYED]

  return !!notEditableStatus.find(s => s === status)
}

export function isEditableStatus(status: OrderStatusEnum) {
  return !isNotEditableStatus(status)
}

export const disabledStatuses = [
  OrderStatusEnum.CREATED,
  OrderStatusEnum.DECLINED,
  OrderStatusEnum.PAYED,
  OrderStatusEnum.APPROVED,
  OrderStatusEnum.IN_PROGRESS,
  OrderStatusEnum.COMPLETED
]

export function orderTypeToString(type: OrderTypeString): string {
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
