import { Order } from '@models/order/order'
import { OrderStatusEnum } from '@models/order-status-enum'

export type StatusFilter = OrderStatusEnum | 'ALL'

export function filterByStatusFunction(filterParam: StatusFilter | StatusFilter[]): (order: Order) => boolean {
  if (Array.isArray(filterParam)) {
    return order => filterParam.includes(order.status.name)
  } else if (filterParam === 'ALL') {
    return () => true
  } else {
    return order => order.status.name === filterParam
  }
}
