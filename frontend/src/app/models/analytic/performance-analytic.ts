import { OrderTypeString } from "@models/order-type-string";
import { OrderStatusEnum } from "@models/order-status-enum";

export interface PerformanceAnalytic {
  orderStatusPercents: {
    status: OrderStatusEnum,
    percent: number
  }[]
}
