import { FormGroup } from '@angular/forms';
import { Order } from '@models/order/order';
import { OrderStatusEnum } from '@models/order-status-enum';
import { SweepOrder } from '@models/order/sweep-order'
import { PickpocketingOrder } from '@models/order/pickpocketing-order'
import { ForgeryOrder } from '@models/order/forgery-order'
import { createOrderFormForForgery } from '@app/client/order/order-form/forgery-order-form/forgery-order-form'
import { createOrderFormForPickpocketing } from '@app/client/order/order-form/pickpocketing-order-form/pickpocketing-order-form'
import { createOrderFormForSweep } from '@app/client/order/order-form/sweep-order-form/sweep-order-form'

const disabledStatuses = [
  OrderStatusEnum.CREATED,
  OrderStatusEnum.DECLINED,
  OrderStatusEnum.PAYED,
  OrderStatusEnum.APPROVED,
  OrderStatusEnum.IN_PROGRESS,
  OrderStatusEnum.COMPLETED
]

export function createOrderForm(order: Order): FormGroup {
  const disabled = order.status && disabledStatuses.includes(OrderStatusEnum[order.status.name])
  let form: FormGroup

  switch (order.type) {
    case 'PICKPOCKETING':
      form = createOrderFormForPickpocketing(order as PickpocketingOrder)
      break
    case 'SWEEP':
      form = createOrderFormForSweep(order as SweepOrder)
      break
    case 'FORGERY':
      form = createOrderFormForForgery(order as ForgeryOrder)
      break
    default:
      throw new Error('Unsupported order type')
  }
  if (disabled) {
    form.disable()
  }

  return form
}

