import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Order } from '@models/order/order';
import { OrderStatusEnum } from '@models/order-status-enum';
import { SweepOrder } from '@models/order/sweep-order'
import { PickpocketingOrder } from '@models/order/pickpocketing-order'
import { ForgeryOrder } from '@models/order/forgery-order'
import { createOrderFormForForgery } from '@app/client/order/order-form/forgery-order-form/forgery-order-form'
import { createOrderFormForPickpocketing } from '@app/client/order/order-form/pickpocketing-order-form/pickpocketing-order-form'
import { createOrderFormForSweep } from '@app/client/order/order-form/sweep-order-form/sweep-order-form'

export function createClientOrderForm(order: Order): FormGroup {
  const disabledStatuses = [
    OrderStatusEnum.CREATED,
    OrderStatusEnum.DECLINED,
    OrderStatusEnum.PAYED,
    OrderStatusEnum.APPROVED,
    OrderStatusEnum.IN_PROGRESS,
    OrderStatusEnum.COMPLETED
  ]

  const disabled = order.status && disabledStatuses.includes(OrderStatusEnum[order.status.name])
  const form = createOrderForm(order)
  if (disabled) {
    form.disable()
  }

  return form
}

export function createMasterOrderForm(order: Order) {
  const enabledStatuses = [
    OrderStatusEnum.CREATED,
    OrderStatusEnum.NEED_CHANGES,
    OrderStatusEnum.APPROVED
  ]

  const disabled = !enabledStatuses.includes(OrderStatusEnum[order.status.name])
  const form = new FormGroup({
    comment: new FormControl(order.comment, [Validators.required]),
    contractor: new FormControl(order.contractor),
    price: new FormControl(order.price, [Validators.required])
  })
  if (disabled) {
    form.disable()
  }

  return form
}

function createOrderForm(order: Order): FormGroup {
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



