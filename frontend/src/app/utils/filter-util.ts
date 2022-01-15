import { Order } from '@models/order/order'
import { OrderStatusEnum } from '@models/order-status-enum'
import {UserRole} from "@models/user-role";
import {User} from "@models/user";

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

export type RoleFilter = UserRole | 'ALL'

export function filterByRoleFunction(filterParam: RoleFilter | RoleFilter[]): (user: User) => boolean {
  if (Array.isArray(filterParam)) {
    return user => filterParam.includes(user.role)
  } else if (filterParam === 'ALL') {
    return () => true
  } else {
    return user => user.role === filterParam
  }
}
